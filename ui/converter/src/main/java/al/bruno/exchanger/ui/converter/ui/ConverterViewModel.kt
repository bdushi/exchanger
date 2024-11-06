package al.bruno.exchanger.ui.converter.ui

import al.bruno.exchanger.common.core.Result
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.TransactionType
import al.bruno.exchanger.currency.converter.api.usecase.GetBalanceUseCase
import al.bruno.exchanger.currency.converter.api.usecase.InsertTransactionUseCase
import al.bruno.exchanger.exchange.api.usecase.GetExchangeRateUseCase
import al.bruno.exchanger.ui.converter.ext.mapBalanceToUIModel
import al.bruno.exchanger.ui.converter.ext.mapExchangeRateToUIModel
import al.bruno.exchanger.ui.converter.model.BalanceUI
import al.bruno.exchanger.ui.converter.model.ConversionState
import al.bruno.exchanger.ui.converter.model.ExchangeRateUI
import al.bruno.exchanger.ui.converter.model.RateUI
import al.bruno.exchanger.ui.converter.model.calculateConvertedRate
import al.bruno.exchanger.ui.converter.model.calculateInverseConvertedRate
import al.bruno.exchanger.ui.foundation.arch.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class ConverterViewModel(
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val insertTransactionUseCase: InsertTransactionUseCase,
    private val getBalanceUseCase: GetBalanceUseCase
    ): ViewModel() {


    private val _uiState: MutableStateFlow<ConversionState> = MutableStateFlow(ConversionState())
    val uiState: StateFlow<ConversionState> = _uiState.asStateFlow()

    val processIntent: (Event) -> Unit = { intent ->
        when (intent) {
            is Event.GetExchangeRate -> {
                exchangeRate()
            }
        }
    }

    init {
        balance()
        exchangeRate()
    }

    fun insert(balanceUI: BalanceUI) {
        viewModelScope.launch(Dispatchers.IO) {
            val toRate = _uiState.value.toRate ?: return@launch
            val fromAmount = _uiState.value.fromValue.toDoubleOrNull() ?: return@launch
            val fromRate = _uiState.value.fromRate ?: return@launch
            try {
                _uiState.value = _uiState.value.copy(
                    transactionUI = State.Success(
                        insertTransactionUseCase(
                            listOf(
                                Transaction(
                                    id = 0,
                                    transactionType = TransactionType.RECEIVE,
                                    value = fromAmount * toRate.rates,
                                    balanceId = balanceUI.id,
                                    commission = 1.0,
                                    currency = toRate.currency,
                                    rate = toRate.rates,
                                    dateCreated = LocalDate.now(),
                                    lastUpdated = LocalDate.now()
                                ),
                                Transaction(
                                    id = 0,
                                    transactionType = TransactionType.SELL,
                                    value = fromAmount,
                                    balanceId = balanceUI.id,
                                    commission = 1.0,
                                    currency = fromRate.base,
                                    rate = 1.0,
                                    dateCreated = LocalDate.now(),
                                    lastUpdated = LocalDate.now()
                                )
                            )
                        )
                    )
                )
            } catch (ex: Exception) {
                _uiState.value = _uiState.value.copy(transactionUI = State.Error("Error"))
            }
        }
    }

    fun onFromValueChange(value: String) {
        _uiState.value = _uiState.value.copy(
            fromValue = value,
            toValue = _uiState.value.toRate?.calculateConvertedRate(value) ?: ""
        )
    }

    fun onToValueChange(value: String) {
        _uiState.value = _uiState.value.copy(
            toValue = value,
            fromValue = _uiState.value.toRate?.calculateInverseConvertedRate(value) ?: ""
        )
    }

    fun onFromCurrencySelected(selectedRate: ExchangeRateUI) {
        _uiState.value = _uiState.value.copy(
            fromRate = selectedRate,
            availableRates = selectedRate.rates.map { RateUI(it.key, it.value) }
        )
    }

    fun onToCurrencySelected(selectedRate: RateUI) {
        _uiState.value = _uiState.value.copy(
            toRate = selectedRate,
            toValue = selectedRate.calculateConvertedRate(_uiState.value.fromValue)
        )
    }

    private fun exchangeRate() =
        viewModelScope.launch(Dispatchers.IO) {
            getExchangeRateUseCase()
                .collect { exchangeRate ->
                    when (exchangeRate) {
                        is Result.Error -> _uiState.value =
                            _uiState.value.copy(exchangeRateUI = State.Error(exchangeRate.error))

                        is Result.Success -> _uiState.value =
                            _uiState.value.copy(exchangeRateUI = State.Success(exchangeRate.data.map { it.mapExchangeRateToUIModel() }))
                    }
                }
        }

    private fun balance() = viewModelScope.launch {
        try {
            _uiState.value =
                _uiState.value.copy(balanceUI = State.Success(getBalanceUseCase().mapBalanceToUIModel()))
        } catch (ex: Exception) {
            _uiState.value = _uiState.value.copy(balanceUI = State.Error("Error"))
        }
    }
}
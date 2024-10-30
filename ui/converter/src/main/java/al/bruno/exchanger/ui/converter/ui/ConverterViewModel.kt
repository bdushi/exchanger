package al.bruno.exchanger.ui.converter.ui

import al.bruno.exchanger.common.core.Result
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.Type
import al.bruno.exchanger.currency.converter.api.usecase.GetBalanceUseCase
import al.bruno.exchanger.currency.converter.api.usecase.InsertTransactionUseCase
import al.bruno.exchanger.exchange.api.usecase.GetExchangeRateUseCase
import al.bruno.exchanger.ui.converter.ext.mapExchangeRateToUIModel
import al.bruno.exchanger.ui.foundation.arch.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class ConverterViewModel(
    getExchangeRateUseCase: GetExchangeRateUseCase,
    private val insertTransactionUseCase: InsertTransactionUseCase,
    private val getBalanceUseCase: GetBalanceUseCase
    ): ViewModel() {
    private val retryTrigger = MutableStateFlow(Unit)

    val processIntent: (ConverterIntent) -> Unit = { intent ->
        when (intent) {
            is ConverterIntent.GetExchangeRate -> {
                onValueChange()
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val exchangeRate =
        retryTrigger.flatMapLatest {
            getExchangeRateUseCase()
                .map { exchangeRate ->
                    when (exchangeRate) {
                        is Result.Error -> State.Error(
                            exchangeRate.error
                        )

                        is Result.Success -> State.Success(
                            exchangeRate.data.map {
                                it.mapExchangeRateToUIModel()
                            }
                        )
                    }
                }.stateIn(
                    // basically convert the Flow returned from combine operator to StateFlow
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),//it will allow the StateFlow survive 5 seconds before it been canceled
                    initialValue = State.Loading
                )
        }

    private val onValueChange: () -> Unit = {
        retryTrigger.value = Unit
    }

    fun insert() {
        viewModelScope.launch(Dispatchers.IO) {
            insertTransactionUseCase(
                Transaction(
                    0,
                    Type.SELL,
                    900.0,
                    1,
                    1.0,
                    "EUR",
                    LocalDate.now(),
                    LocalDate.now()
                )
            )
        }
    }

//    fun balance() = viewModelScope.launch {
//        getBalanceUseCase()
//    }
}
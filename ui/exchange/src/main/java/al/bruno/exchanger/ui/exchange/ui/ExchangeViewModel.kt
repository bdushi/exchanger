package al.bruno.exchanger.ui.exchange.ui

import al.bruno.exchanger.currency.converter.api.usecase.GetTransactionUseCase
import al.bruno.exchanger.exchange.api.usecase.GetExchangeUseCase
import al.bruno.exchanger.ui.exchange.ext.mapExchangeToUIModel
import al.bruno.exchanger.ui.exchange.ext.mapTransactionUIModel
import al.bruno.exchanger.ui.exchange.model.ExchangeUI
import al.bruno.exchanger.ui.exchange.model.TransactionUI
import al.bruno.exchanger.ui.foundation.arch.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ExchangeViewModel(
    private val getExchangeUseCase: GetExchangeUseCase,
    private val getTransactionUseCase: GetTransactionUseCase
) : ViewModel() {
    // Private mutable StateFlow for Exchange states, initially set to Loading
    private val _exchange = MutableStateFlow<State<List<ExchangeUI>>>(State.Loading)
    // Publicly exposed StateFlow that provides read-only access to _exchange
    val exchange: StateFlow<State<List<ExchangeUI>>> get() = _exchange

    // Private mutable StateFlow for Exchange states, initially set to Loading
    private val _transaction = MutableStateFlow<State<List<TransactionUI>>>(State.Loading)
    // Publicly exposed StateFlow that provides read-only access to _transaction
    val transaction: StateFlow<State<List<TransactionUI>>> get() = _transaction

    val processIntent: (Event) -> Unit = { intent ->
        when (intent) {
            Event.Exchange -> {
                exchanges()
            }

            Event.Transaction -> {
                transaction()
            }
        }
    }

    private fun exchanges() {
        viewModelScope.launch {
            getExchangeUseCase()
                .catch {
                    _exchange.value = State.Error(it.message)
                }
                .collect { exchange ->
                    _exchange.value = State.Success(
                        exchange.map { it.mapExchangeToUIModel() }
                    )
                }
        }
    }

    private fun transaction() {
        viewModelScope.launch {
            getTransactionUseCase()
                .catch {
                    _transaction.value = State.Error(it.message)
                }
                .collect { transaction ->
                    _transaction.value = State.Success(
                        transaction.map { it.mapTransactionUIModel() }
                    )
                }
        }
    }
}
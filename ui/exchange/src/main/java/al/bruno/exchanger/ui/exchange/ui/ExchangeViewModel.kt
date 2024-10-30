package al.bruno.exchanger.ui.exchange.ui

import al.bruno.exchanger.currency.converter.api.usecase.GetTransactionUseCase
import al.bruno.exchanger.exchange.api.usecase.GetExchangeUseCase
import al.bruno.exchanger.ui.exchange.ext.mapExchangeToUIModel
import al.bruno.exchanger.ui.exchange.ext.mapTransactionUIModel
import al.bruno.exchanger.ui.exchange.model.ExchangeUI
import al.bruno.exchanger.ui.foundation.arch.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExchangeViewModel(
    private val getExchangeUseCase: GetExchangeUseCase,
    private val getTransactionUseCase: GetTransactionUseCase
) : ViewModel() {
    // Private mutable StateFlow for Exchange states, initially set to Loading
    private val _exchange = MutableStateFlow<State<List<ExchangeUI>>>(State.Loading)

    // Publicly exposed StateFlow that provides read-only access to _exchange
    val exchange: StateFlow<State<List<ExchangeUI>>> get() = _exchange

    val processIntent: (Event) -> Unit = { intent ->
        when (intent) {
            Event.Exchange -> {
                exchanges()
            }

            Event.Transaction -> {

            }
        }
    }

    private fun exchanges() {
        viewModelScope.launch {
            getExchangeUseCase()
                .catch { e ->
                    _exchange.value = State.Error(e.message ?: "Unknown error")
                }
                .collect { data ->
                    _exchange.value = State.Success(
                        data.map { it.mapExchangeToUIModel() }
                    )
                }
        }
    }

    val transaction =
        getTransactionUseCase()
            .catch {
                State.Error(it.message)
            }
            .map { transaction ->
                State.Success(transaction.map { it.mapTransactionUIModel() })
        }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = State.Loading
        )
}
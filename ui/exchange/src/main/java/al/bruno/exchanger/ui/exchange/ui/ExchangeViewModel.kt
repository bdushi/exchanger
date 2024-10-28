package al.bruno.exchanger.ui.exchange.ui

import al.bruno.exchanger.common.core.Result
import al.bruno.exchanger.exchange.api.usecase.GetExchangeRateUseCase
import al.bruno.exchanger.ui.exchange.ext.mapExchangeRateToUIModel
import al.bruno.exchanger.ui.foundation.arch.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ExchangeViewModel(getExchangeRateUseCase: GetExchangeRateUseCase) : ViewModel() {

    // second state the text typed by the user
    private val getExchangeRate = MutableStateFlow("")

    val processIntent: (Event) -> Unit = { intent ->
        when (intent) {
            is Event.GetExchange -> {
                getExchangeRate.value = ""
            }

            is Event.AddMessage -> {

            }

            is Event.DeleteMessage -> {

            }

            is Event.EditMessage -> {

            }
        }
    }

    val exchangeRate =
        getExchangeRateUseCase().map { exchangeRate ->
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
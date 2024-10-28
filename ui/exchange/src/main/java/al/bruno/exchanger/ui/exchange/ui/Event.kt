package al.bruno.exchanger.ui.exchange.ui

import al.bruno.exchanger.ui.foundation.arch.UiEvent

sealed class Event: UiEvent {
    data object GetExchange : Event()
    data class AddMessage(val content: String) : Event()
    data class DeleteMessage(val id: String) : Event()
    data class EditMessage(val id: String) : Event()
}
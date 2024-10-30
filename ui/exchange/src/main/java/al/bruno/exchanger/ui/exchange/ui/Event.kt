package al.bruno.exchanger.ui.exchange.ui

import al.bruno.exchanger.ui.foundation.arch.UiEvent

sealed class Event: UiEvent {
    data object Exchange : Event()
    data object Transaction : Event()
}
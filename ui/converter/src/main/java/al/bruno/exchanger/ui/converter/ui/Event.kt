package al.bruno.exchanger.ui.converter.ui

sealed class Event {
    data object GetExchangeRate : Event()
}
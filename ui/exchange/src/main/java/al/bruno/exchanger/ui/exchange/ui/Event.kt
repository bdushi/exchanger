package al.bruno.exchanger.ui.exchange.ui


sealed class Event {
    data object Exchange : Event()
    data object Transaction : Event()
}
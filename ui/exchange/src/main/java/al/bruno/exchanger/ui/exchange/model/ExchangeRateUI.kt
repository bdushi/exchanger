package al.bruno.exchanger.ui.exchange.model

data class ExchangeRateUI(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
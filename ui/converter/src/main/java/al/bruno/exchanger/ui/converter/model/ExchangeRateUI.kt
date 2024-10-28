package al.bruno.exchanger.ui.converter.model

data class ExchangeRateUI(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
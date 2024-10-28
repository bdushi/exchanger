package al.bruno.exchanger.exchange.api.domain

data class ExchangeRate(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
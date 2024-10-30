package al.bruno.exchanger.exchange.api.domain

data class Exchange(
    val balance: Double,
    val currency: String,
    val commission: Double,
)
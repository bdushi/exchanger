package al.bruno.exchanger.currency.converter.api.domain

data class NewTransaction(
    val balanceId: Long,
    val currency: String,
    val rates: Double,
    val fromAmount: Double,
    val base: String,
)
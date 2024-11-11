package al.bruno.exchanger.currency.converter.api.domain

data class Commission(
    val transactionId: Long,
    val type: CommissionType,
    val fee: Double,
    val rate: Double,
    val base: String,
)
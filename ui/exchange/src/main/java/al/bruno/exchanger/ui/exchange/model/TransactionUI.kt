package al.bruno.exchanger.ui.exchange.model

data class TransactionUI(
    val id: Long,
    val type: TypeUI,
    val value: Double,
    val commission: Double,
    val currency: String,
)
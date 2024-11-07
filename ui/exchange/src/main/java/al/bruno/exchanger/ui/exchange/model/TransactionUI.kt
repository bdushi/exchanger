package al.bruno.exchanger.ui.exchange.model

data class TransactionUI(
    val id: Long,
    val type: TransactionTypeUI,
    val value: Double,
    val commission: CommissionUI,
    val currency: String,
)
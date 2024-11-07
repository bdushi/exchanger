package al.bruno.exchanger.ui.converter.model

data class CommissionUI(
    val sellValue: Double?,
    val sellCurrency: String?,
    val receiveValue: Double?,
    val receiveCurrency: String?,
    val commission: Double?,
)
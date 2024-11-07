package al.bruno.exchanger.ui.exchange.model

data class CommissionUI(
    val commissionType: CommissionTypeUI = CommissionTypeUI.FREE,
    val commission: Double = .0,
    val commissionRate: Double = .0
)
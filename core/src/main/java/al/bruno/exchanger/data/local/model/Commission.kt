package al.bruno.exchanger.data.local.model

data class Commission(
    val commissionType: CommissionType,
    val commission: Double,
    val commissionRate: Double
)
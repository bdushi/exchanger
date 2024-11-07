package al.bruno.exchanger.currency.converter.api.domain

data class Commission(
    val commissionType: CommissionType = CommissionType.FREE,
    val commission: Double = .0,
    val commissionRate: Double = .0
)
package al.bruno.exchanger.currency.converter.impl.decorator

import al.bruno.exchanger.currency.converter.api.domain.CommissionType

// Concrete commission rules
class StandardCommissionRule(rate: Double) : BaseCommissionRule(
    CommissionType.COMMISSION,
    { rate }
)

class RewardRule(rate: Double) : BaseCommissionRule(
    CommissionType.BONUS,
    { rate }
)

class DiscountRule(rate: Double) : BaseCommissionRule(
    CommissionType.COMMISSION,
    { rate }
)

class FreeExchangeRule(rate: Double) : BaseCommissionRule(
    CommissionType.FREE,
    { rate }
)
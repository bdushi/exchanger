package al.bruno.exchanger.currency.converter.impl.rules

import al.bruno.exchanger.currency.converter.api.domain.RuleType
import al.bruno.exchanger.currency.converter.api.rules.ExchangeRuleStrategy

class ExchangeRuleStrategyFactory {
    fun getRuleStrategy(
        action: RuleType,
        transactionCount: Int
    ): ExchangeRuleStrategy {
        return when (action) {
            RuleType.COMMISSION -> ConditionalExchangeRule(CommissionRule()) {
                transactionCount >= 5
            }
            RuleType.REWARD -> ConditionalExchangeRule(RewardRule()) {
                transactionCount >= 10
            }
            RuleType.DISCOUNT -> ConditionalExchangeRule(DiscountRule()) {
                transactionCount >= 20
            }
            RuleType.FREE_EXCHANGE -> ConditionalExchangeRule(FreeExchangeRule()) {
                transactionCount % 10 == 0
            }
            RuleType.FREE_UP_TO_LIMIT -> ConditionalExchangeRule(FreeUpToLimitRule()) {
                it.value >= 200
            }
        }
    }
}
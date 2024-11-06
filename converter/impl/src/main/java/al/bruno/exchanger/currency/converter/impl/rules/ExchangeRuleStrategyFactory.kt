package al.bruno.exchanger.currency.converter.impl.rules

import al.bruno.exchanger.currency.converter.api.domain.RuleType
import al.bruno.exchanger.currency.converter.api.rules.ExchangeRuleStrategy

class ExchangeRuleStrategyFactory {
    fun getRuleStrategy(action: RuleType, transactionCount: Int): ExchangeRuleStrategy {
        return when (action) {
            RuleType.COMMISSION -> ConditionalExchangeRuleStrategy(CommissionRule()) {
                transactionCount >= 5
            }
            RuleType.REWARD -> ConditionalExchangeRuleStrategy(RewardRule()) {
                transactionCount >= 10
            }
            RuleType.DISCOUNT -> ConditionalExchangeRuleStrategy(DiscountRule()) {
                transactionCount >= 20
            }
            RuleType.FREE_EXCHANGE -> ConditionalExchangeRuleStrategy(FreeExchangeRule()) {
                transactionCount % 10 == 0
            }
            RuleType.FREE_UP_TO_LIMIT -> ConditionalExchangeRuleStrategy(FreeUpToLimitRule()) {
                it.value <= 200
            }
        }
    }
}
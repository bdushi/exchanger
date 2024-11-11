package al.bruno.exchanger.currency.converter.impl.decorator

import al.bruno.exchanger.currency.converter.api.domain.RuleType
import al.bruno.exchanger.currency.converter.api.rules.ExchangeRule

class ExchangeRuleFactory {
    private fun createRule(
        ruleType: RuleType,
        rate: Double,
        transactionCounter: () -> Int
    ): ExchangeRule {
        return when (ruleType) {
            RuleType.COMMISSION -> ConditionalRuleDecorator(
                StandardCommissionRule(rate)
            ) {
                transactionCounter() >= 5
            }

            RuleType.REWARD -> ConditionalRuleDecorator(
                RewardRule(rate)
            ) { transactionCounter() >= 10 }

            RuleType.DISCOUNT -> ConditionalRuleDecorator(
                DiscountRule(rate)
            ) { transactionCounter() >= 20 }

            RuleType.FREE_EXCHANGE -> ConditionalRuleDecorator(
                FreeExchangeRule(rate)
            ) { transactionCounter() % 10 == 0 }

            RuleType.FREE_UP_TO_LIMIT -> ConditionalRuleDecorator(
                FreeExchangeRule(rate)
            ) { it.value >= 200 }
        }
    }

    fun createCompositeRule(
        vararg ruleConfigs: Pair<RuleType, Double>,
        transactionCounter: () -> Int
    ): ExchangeRule {
        return CompositeExchangeRule(
            ruleConfigs.map { (type, rate) ->
                createRule(type, rate, transactionCounter)
            }
        )
    }
}
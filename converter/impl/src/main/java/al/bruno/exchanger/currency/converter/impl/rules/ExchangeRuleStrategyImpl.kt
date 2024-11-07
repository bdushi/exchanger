package al.bruno.exchanger.currency.converter.impl.rules

import al.bruno.exchanger.currency.converter.api.domain.Commission
import al.bruno.exchanger.currency.converter.api.domain.CommissionType
import al.bruno.exchanger.currency.converter.api.domain.ExchangeRule
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.rules.ExchangeRuleStrategy

class CommissionRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule): Transaction {
        return transaction.copy(
            commission = Commission(
                commission = transaction.value * rule.value,
                commissionType = CommissionType.COMMISSION,
                commissionRate = rule.value
            )
        )
    }
}

class RewardRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule): Transaction {
        return transaction.copy(
            commission = Commission(
                commission = transaction.value * rule.value,
                commissionType = CommissionType.BONUS,
                commissionRate = rule.value
            )
        )
    }
}

class DiscountRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule): Transaction {
        return transaction.copy(
            commission = Commission(
                commission = transaction.value * rule.value,
                commissionType = CommissionType.COMMISSION,
                commissionRate = rule.value
            )
        )
    }
}

class FreeExchangeRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule): Transaction {
        return transaction.copy(
            commission = Commission(
                commission = 0.0, commissionType = CommissionType.FREE, commissionRate = 0.0
            )
        )
    }
}

class FreeUpToLimitRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule): Transaction {
        return transaction.copy(
            commission = Commission(
                commission = 0.0, commissionType = CommissionType.FREE, commissionRate = 0.0
            )
        )
    }
}

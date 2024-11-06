package al.bruno.exchanger.currency.converter.impl.rules

import al.bruno.exchanger.currency.converter.api.domain.ExchangeRule
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.rules.ExchangeRuleStrategy

class CommissionRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule): Transaction {
        return transaction.copy(commission = transaction.value * rule.value)
    }
}

class RewardRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule): Transaction {
        return transaction.copy(commission = transaction.value * rule.value)
    }
}

class DiscountRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule): Transaction {
        return transaction.copy(commission = transaction.value * rule.value)
    }
}

class FreeExchangeRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule): Transaction {
        return transaction.copy(commission = 0.0)
    }
}

class FreeUpToLimitRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule): Transaction {
        return transaction.copy(commission = 0.0)
    }
}

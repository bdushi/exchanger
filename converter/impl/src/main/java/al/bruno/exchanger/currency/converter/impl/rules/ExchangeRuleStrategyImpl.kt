package al.bruno.exchanger.currency.converter.impl.rules

import al.bruno.exchanger.currency.converter.api.domain.Commission
import al.bruno.exchanger.currency.converter.api.domain.CommissionType
import al.bruno.exchanger.currency.converter.api.domain.ExchangeRule
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.rules.ExchangeRuleStrategy

class CommissionRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule) =
        Commission(
            transactionId = transaction.id,
            type = CommissionType.COMMISSION,
            fee = transaction.value * rule.rate,
            rate = rule.rate,
            base = transaction.currency
        )
}

class RewardRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule) =
        Commission(
            transactionId = transaction.id,
            type = CommissionType.BONUS,
            fee = transaction.value * rule.rate,
            rate = rule.rate,
            base = transaction.currency
        )
}

class DiscountRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule) =
        Commission(
            transactionId = transaction.id,
            type = CommissionType.COMMISSION,
            fee = transaction.value * rule.rate,
            rate = rule.rate,
            base = transaction.currency
        )
}

class FreeExchangeRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule) =
        Commission(
            transactionId = transaction.id,
            type = CommissionType.FREE,
            fee = 0.0,
            rate = 0.0,
            base = transaction.currency
        )
}

class FreeUpToLimitRule : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule) =
        Commission(
            transactionId = transaction.id,
            type = CommissionType.FREE,
            fee = 0.0,
            rate = 0.0,
            base = transaction.currency
        )
}

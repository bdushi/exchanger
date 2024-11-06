package al.bruno.exchanger.currency.converter.impl.rules

import al.bruno.exchanger.currency.converter.api.domain.ExchangeRule
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.rules.ExchangeRuleStrategy

class ConditionalExchangeRuleStrategy(
    private val wrappedStrategy: ExchangeRuleStrategy,
    private val condition: (transaction: Transaction) -> Boolean
) : ExchangeRuleStrategy {
    override fun applyRule(transaction: Transaction, rule: ExchangeRule): Transaction {
        return if (condition(transaction)) {
            wrappedStrategy.applyRule(transaction, rule)
        } else {
            transaction
        }
    }
}
package al.bruno.exchanger.currency.converter.impl.decorator

import al.bruno.exchanger.currency.converter.api.domain.Commission
import al.bruno.exchanger.currency.converter.api.rules.ExchangeRule
import al.bruno.exchanger.currency.converter.api.domain.Transaction

class ConditionalRuleDecorator(
    exchangeRule: ExchangeRule,
    private val condition: (Transaction) -> Boolean
) : ExchangeRuleDecorator(exchangeRule) {
    override fun apply(transaction: Transaction): Commission? {
        return if (condition(transaction)) {
            super.apply(transaction)
        } else {
            null
        }
    }
}
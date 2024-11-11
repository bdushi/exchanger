package al.bruno.exchanger.currency.converter.impl.decorator

import al.bruno.exchanger.currency.converter.api.domain.Commission
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.rules.ExchangeRule

abstract class ExchangeRuleDecorator (private val exchangeRule: ExchangeRule) : ExchangeRule {
    override fun apply(transaction: Transaction): Commission? {
        return exchangeRule.apply(transaction)
    }
}
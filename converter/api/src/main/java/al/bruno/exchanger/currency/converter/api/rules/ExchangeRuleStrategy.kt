package al.bruno.exchanger.currency.converter.api.rules

import al.bruno.exchanger.currency.converter.api.domain.ExchangeRule
import al.bruno.exchanger.currency.converter.api.domain.Transaction

interface ExchangeRuleStrategy {
    fun applyRule(transaction: Transaction, rule: ExchangeRule): Transaction
}
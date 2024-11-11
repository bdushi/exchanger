package al.bruno.exchanger.currency.converter.api.rules

import al.bruno.exchanger.currency.converter.api.domain.Commission
import al.bruno.exchanger.currency.converter.api.domain.Transaction

// Core interfaces and base classes
interface ExchangeRule {
    fun apply(transaction: Transaction): Commission?
}
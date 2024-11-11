package al.bruno.exchanger.currency.converter.impl.decorator

import al.bruno.exchanger.currency.converter.api.domain.Commission
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.rules.ExchangeRule

// Composite rule that can combine multiple rules
class CompositeExchangeRule(
    private val rules: List<ExchangeRule>
) : ExchangeRule {
    override fun apply(transaction: Transaction): Commission? {
        return rules.firstNotNullOfOrNull {
            it.apply(transaction)
        }
    }
}
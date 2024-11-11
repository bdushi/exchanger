package al.bruno.exchanger.currency.converter.impl.decorator

import al.bruno.exchanger.currency.converter.api.domain.Commission
import al.bruno.exchanger.currency.converter.api.domain.CommissionType
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.rules.ExchangeRule

// Base commission calculator that implements the core calculation logic
abstract class BaseCommissionRule(
    private val type: CommissionType,
    private val rateCalculator: (Transaction) -> Double
) : ExchangeRule {
    override fun apply(transaction: Transaction): Commission {
        val rate = rateCalculator(transaction)
        return Commission(
            transactionId = transaction.id,
            type = type,
            fee = transaction.value * rate,
            rate = rate,
            base = transaction.currency
        )
    }
}
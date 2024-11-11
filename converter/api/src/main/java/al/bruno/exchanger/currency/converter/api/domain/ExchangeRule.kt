package al.bruno.exchanger.currency.converter.api.domain

import java.time.LocalDate

data class ExchangeRule(
    val id: Long,
    val priority: Int, // Rule priority order
    val description: String,
    val condition: String, // Condition as a string (e.g., "transactionCount >= 5")
    val type: RuleType, // Enum to define the action to apply
    val rate: Double, // rate for the key (e.g., 0.007, 200.0)
    val dateCreated: LocalDate,
    val lastUpdated: LocalDate
)

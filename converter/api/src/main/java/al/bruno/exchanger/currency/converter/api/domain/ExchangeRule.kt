package al.bruno.exchanger.currency.converter.api.domain

import java.time.LocalDate

data class ExchangeRule(
    val id: Long,
    val description: String,
    val condition: String, // Condition as a string (e.g., "transactionCount >= 5")
    val action: RuleType, // Enum to define the action to apply
    val value: Double, // Value for the key (e.g., 0.007, 200.0)
    val dateCreated: LocalDate,
    val lastUpdated: LocalDate
)

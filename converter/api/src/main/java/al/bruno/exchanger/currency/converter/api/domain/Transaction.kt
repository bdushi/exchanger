package al.bruno.exchanger.currency.converter.api.domain

import java.time.LocalDate

data class Transaction(
    val id: Long,
    val type: Type,
    val value: Double,
    val balanceId: Long,
    val commission: Double,
    val currency: String,
    val dateCreated: LocalDate,
    val lastUpdated: LocalDate
)
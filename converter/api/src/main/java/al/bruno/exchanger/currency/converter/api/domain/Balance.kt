package al.bruno.exchanger.currency.converter.api.domain

import java.time.LocalDate

data class Balance(
    val id: Long,
    val amount: Double,
    val currency: String,
    val dateCreated: LocalDate,
    val lastUpdated: LocalDate
)
package al.bruno.exchanger.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val type: Type,
    val value: Double,
    val balanceId: Long,
    val commission: Double,
    val createdDate: LocalDate,
    val update: LocalDate
)

package al.bruno.exchanger.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Balance(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val amount: Double,
    val currency: String,
    val dateCreated: LocalDate,
    val lastUpdated: LocalDate
)
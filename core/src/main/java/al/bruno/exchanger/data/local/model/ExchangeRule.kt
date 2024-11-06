package al.bruno.exchanger.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class ExchangeRule(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val description: String,
    val condition: String, // Condition as a string (e.g., "transactionCount >= 5")
    val action: RuleType, // Enum to define the action to apply
    val value: Double, // Value for the key (e.g., 0.007, 200.0)
    val dateCreated: LocalDate,
    val lastUpdated: LocalDate
)
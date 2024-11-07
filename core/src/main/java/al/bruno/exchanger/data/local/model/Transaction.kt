package al.bruno.exchanger.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "Transaction",
    foreignKeys = [
        ForeignKey(
            entity = Balance::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("balanceId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val transactionType: TransactionType,
    val value: Double,
    val balanceId: Long,
    val rate: Double,
    val currency: String,
    @Embedded
    val commission: Commission,
    val dateCreated: LocalDate,
    val lastUpdated: LocalDate
)

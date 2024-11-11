package al.bruno.exchanger.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Commission",
    foreignKeys = [
        ForeignKey(
            entity = Transaction::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("transactionId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE,
        )]
)
data class Commission(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val transactionId: Long,
    val type: CommissionType,
    val fee: Double,
    val rate: Double,
    val base: String,
)
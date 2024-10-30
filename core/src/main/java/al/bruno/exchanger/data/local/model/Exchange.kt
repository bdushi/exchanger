package al.bruno.exchanger.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class Exchange(
    @Embedded val balance: Balance,
    @Relation(
        parentColumn = "id",
        entityColumn = "balanceId"
    )
    val transaction: List<Transaction>
)
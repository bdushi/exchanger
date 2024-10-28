package al.bruno.exchanger.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exchange(
    @PrimaryKey(autoGenerate = true)
    val id: Long
)
package al.bruno.exchanger.data.local.dao

import al.bruno.exchanger.data.local.model.Commission
import androidx.room.Dao
import androidx.room.Insert

@Dao
interface CommissionDao {
    @Insert
    suspend fun insert(transactions: List<Commission>): List<Long>
}
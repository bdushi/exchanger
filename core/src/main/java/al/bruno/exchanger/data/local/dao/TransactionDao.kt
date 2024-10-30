package al.bruno.exchanger.data.local.dao

import al.bruno.exchanger.data.local.model.Transaction
import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: Transaction): Long
}
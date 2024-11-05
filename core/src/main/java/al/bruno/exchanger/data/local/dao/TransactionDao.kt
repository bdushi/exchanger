package al.bruno.exchanger.data.local.dao

import al.bruno.exchanger.data.local.model.Transaction
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: Transaction): Long

    @Insert
    suspend fun insert(transactions: List<Transaction>): List<Long>

    @Query("SELECT * FROM `Transaction`")
    fun transaction(): Flow<List<Transaction>>
}
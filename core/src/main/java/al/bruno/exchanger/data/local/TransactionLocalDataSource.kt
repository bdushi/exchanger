package al.bruno.exchanger.data.local

import al.bruno.exchanger.data.local.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionLocalDataSource {
    suspend fun insert(transaction: Transaction): Long
    fun transaction(): Flow<List<Transaction>>
}
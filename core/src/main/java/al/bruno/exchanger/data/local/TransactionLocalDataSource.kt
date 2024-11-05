package al.bruno.exchanger.data.local

import al.bruno.exchanger.data.local.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionLocalDataSource {
    suspend fun insert(transaction: Transaction): Long
    suspend fun insert(transactions: List<Transaction>): List<Long>
    fun transaction(): Flow<List<Transaction>>
}
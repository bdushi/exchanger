package al.bruno.exchanger.currency.converter.api.repository

import al.bruno.exchanger.currency.converter.api.domain.Balance
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import kotlinx.coroutines.flow.Flow

interface ConverterRepository {
    suspend fun insertTransaction(transaction: Transaction): Long
    suspend fun insertTransactions(transactions: List<Transaction>): List<Transaction>
    suspend fun balance(): Balance
    fun transaction(): Flow<List<Transaction>>
}
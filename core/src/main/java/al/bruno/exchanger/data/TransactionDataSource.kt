package al.bruno.exchanger.data

import al.bruno.exchanger.data.local.TransactionLocalDataSource
import al.bruno.exchanger.data.local.dao.TransactionDao
import al.bruno.exchanger.data.local.model.Transaction
import kotlinx.coroutines.flow.Flow

class TransactionDataSource(private val transactionDao: TransactionDao) : TransactionLocalDataSource {
    override suspend fun insert(transaction: Transaction) =
        transactionDao.insert(transaction)

    override suspend fun insert(transactions: List<Transaction>) =
        transactionDao.insert(transactions)

    override fun transaction(): Flow<List<Transaction>> =
        transactionDao.transaction()
}
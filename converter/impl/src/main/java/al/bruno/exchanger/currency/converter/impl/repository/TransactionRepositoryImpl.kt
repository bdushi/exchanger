package al.bruno.exchanger.currency.converter.impl.repository

import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.repository.TransactionRepository
import al.bruno.exchanger.currency.converter.impl.ext.toTransactionEntity
import al.bruno.exchanger.data.local.TransactionLocalDataSource

class TransactionRepositoryImpl(private val transactionLocalDataSource: TransactionLocalDataSource): TransactionRepository {
    override suspend fun insert(transaction: Transaction) =
        transactionLocalDataSource.insert(
            transaction.toTransactionEntity()
        )
}
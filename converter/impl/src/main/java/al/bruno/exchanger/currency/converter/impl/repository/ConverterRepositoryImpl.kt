package al.bruno.exchanger.currency.converter.impl.repository

import al.bruno.exchanger.currency.converter.api.domain.Balance
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.repository.ConverterRepository
import al.bruno.exchanger.currency.converter.impl.ext.toBalance
import al.bruno.exchanger.currency.converter.impl.ext.toTransaction
import al.bruno.exchanger.currency.converter.impl.ext.toTransactionEntity
import al.bruno.exchanger.data.local.BalanceLocalDataSource
import al.bruno.exchanger.data.local.TransactionLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConverterRepositoryImpl(
    private val transactionLocalDataSource: TransactionLocalDataSource,
    private val balanceLocalDataSource: BalanceLocalDataSource
) : ConverterRepository {
    override suspend fun insert(transaction: Transaction) =
        transactionLocalDataSource.insert(
            transaction.toTransactionEntity()
        )

    override suspend fun insert(transactions: List<Transaction>): List<Long> =
        transactionLocalDataSource.insert(transactions.map {
            it.toTransactionEntity()
        })

    override suspend fun balance(): Balance =
        balanceLocalDataSource
            .balance().toBalance()

    override fun transaction(): Flow<List<Transaction>> =
        transactionLocalDataSource
            .transaction()
            .map { flow ->
                flow.map { transaction ->
                    transaction.toTransaction()
                }
            }
}
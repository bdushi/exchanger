package al.bruno.exchanger.currency.converter.impl.repository

import al.bruno.exchanger.currency.converter.api.domain.Balance
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.TransactionType
import al.bruno.exchanger.currency.converter.api.repository.ConverterRepository
import al.bruno.exchanger.currency.converter.impl.ext.toBalance
import al.bruno.exchanger.currency.converter.impl.ext.toExchangeRuleEntity
import al.bruno.exchanger.currency.converter.impl.ext.toRuleType
import al.bruno.exchanger.currency.converter.impl.ext.toTransaction
import al.bruno.exchanger.currency.converter.impl.ext.toTransactionEntity
import al.bruno.exchanger.currency.converter.impl.ext.toTypeEntity
import al.bruno.exchanger.currency.converter.impl.rules.ExchangeRuleStrategyFactory
import al.bruno.exchanger.data.local.BalanceLocalDataSource
import al.bruno.exchanger.data.local.TransactionLocalDataSource
import al.bruno.exchanger.data.local.TransactionRuleLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConverterRepositoryImpl(
    private val transactionLocalDataSource: TransactionLocalDataSource,
    private val balanceLocalDataSource: BalanceLocalDataSource,
    private val transactionRuleLocalDataSource: TransactionRuleLocalDataSource
) : ConverterRepository {
    override suspend fun insertTransaction(transaction: Transaction): Long =
        transactionLocalDataSource.insert(transaction.toTransactionEntity())

    override suspend fun insertTransactions(transactions: List<Transaction>): List<Transaction> {
        // val insertTransaction = transaction.toTransactionEntity()
        val transactionCount =
            transactionLocalDataSource.getTransactionCount(TransactionType.SELL.toTypeEntity())
        // Fetch exchange rules from the local data source
        val exchangeRules = transactionRuleLocalDataSource.getExchangeRules()
        // Initialize the rule strategy factory
        val strategyFactory = ExchangeRuleStrategyFactory()
        return transactions.map { transaction ->
            when (transaction.transactionType) {
                TransactionType.SELL -> {
                    val appliedCommission = exchangeRules.map {
                        strategyFactory
                            .getRuleStrategy(
                                transactionCount = transactionCount,
                                action = it.action.toRuleType()
                            ).applyRule(
                                transaction,
                                it.toExchangeRuleEntity()
                            )
                    }.last()
                    transactionLocalDataSource.insert(
                        appliedCommission.toTransactionEntity()
                    )
                    appliedCommission
                }

                TransactionType.RECEIVE -> {
                    transactionLocalDataSource.insert(transaction.toTransactionEntity())
                    transaction
                }
            }
        }
    }

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
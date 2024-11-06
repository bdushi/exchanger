package al.bruno.exchanger.currency.converter.impl.repository

import al.bruno.exchanger.currency.converter.api.domain.Balance
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.repository.ConverterRepository
import al.bruno.exchanger.currency.converter.impl.ext.toBalance
import al.bruno.exchanger.currency.converter.impl.ext.toExchangeRuleEntity
import al.bruno.exchanger.currency.converter.impl.ext.toRuleType
import al.bruno.exchanger.currency.converter.impl.ext.toTransaction
import al.bruno.exchanger.currency.converter.impl.ext.toTransactionEntity
import al.bruno.exchanger.currency.converter.impl.rules.ExchangeRuleStrategyFactory
import al.bruno.exchanger.data.local.BalanceLocalDataSource
import al.bruno.exchanger.data.local.TransactionLocalDataSource
import al.bruno.exchanger.data.local.TransactionRuleLocalDataSource
import al.bruno.exchanger.data.local.model.TransactionType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConverterRepositoryImpl(
    private val transactionLocalDataSource: TransactionLocalDataSource,
    private val balanceLocalDataSource: BalanceLocalDataSource,
    private val transactionRuleLocalDataSource: TransactionRuleLocalDataSource
) : ConverterRepository {
    override suspend fun insert(transaction: Transaction): Long {
        val insertTransaction = transaction.toTransactionEntity()
        val transactionCount = transactionLocalDataSource.getTransactionCount(TransactionType.SELL)
        // Fetch exchange rules from the local data source
        val exchangeRules = transactionRuleLocalDataSource.getExchangeRules()

        // Initialize the rule strategy factory
        val strategyFactory = ExchangeRuleStrategyFactory()

        return when (insertTransaction.transactionType) {
            TransactionType.SELL -> {
                exchangeRules.map {
                    val strategy = strategyFactory
                        .getRuleStrategy(
                            transactionCount = transactionCount,
                            action = it.action.toRuleType()
                        )
                    strategy.applyRule(insertTransaction.toTransaction(), it.toExchangeRuleEntity())
                }
                transactionLocalDataSource.insert(insertTransaction)
            }

            TransactionType.RECEIVE -> {
                transactionLocalDataSource.insert(insertTransaction)
            }
        }
    }

    override suspend fun insert(transactions: List<Transaction>): List<Long> {
        return transactions.map { insert(it) }
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
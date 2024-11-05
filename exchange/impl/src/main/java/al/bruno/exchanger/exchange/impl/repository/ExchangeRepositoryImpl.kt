package al.bruno.exchanger.exchange.impl.repository

import al.bruno.exchanger.data.local.BalanceLocalDataSource
import al.bruno.exchanger.data.local.ExchangeLocalDataSource
import al.bruno.exchanger.data.local.TransactionLocalDataSource
import al.bruno.exchanger.data.local.model.Type
import al.bruno.exchanger.data.network.ExchangeNetworkDataSource
import al.bruno.exchanger.exchange.api.domain.Exchange
import al.bruno.exchanger.exchange.api.domain.ExchangeRate
import al.bruno.exchanger.exchange.api.repository.ExchangeRepository
import al.bruno.exchanger.exchange.impl.ext.toExchange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ExchangeRepositoryImpl(
    private val messagesLocalDataSource: ExchangeNetworkDataSource,
    private val balanceDataSource: BalanceLocalDataSource,
    private val exchangeLocalDataSource: ExchangeLocalDataSource,
    private val transactionDataSource: TransactionLocalDataSource,
) : ExchangeRepository {
    override suspend fun getMessages(): List<ExchangeRate> =
        messagesLocalDataSource
            .getExchangeRate()
            .map {
                it.toExchange()
            }

    override suspend fun exchange(): Flow<List<Exchange>> {
        return transactionDataSource
            .transaction()
            .combine(
                flowOf(balanceDataSource.balance())
            ) { transactions, balance ->
                // Initialize a map to keep track of final balances for each currency
                val finalBalances = mutableMapOf<String, Double>()
                // Initialize the balance for the single currency
                balance.let {
                    finalBalances[it.currency] =
                        it.amount  // Set initial balance for the single currency
                }
                // Process transactions
                for (transaction in transactions) {
                    when (transaction.type) {
                        Type.SELL -> {
                            val currentAmount =
                                finalBalances.getOrDefault(transaction.currency, 0.0)
                            finalBalances[transaction.currency] = currentAmount - transaction.value
                        }

                        Type.RECEIVE -> {
                            val currentAmount =
                                finalBalances.getOrDefault(transaction.currency, 0.0)
                            finalBalances[transaction.currency] = currentAmount + transaction.value
                        }
                    }
                }

                // Convert the final balances map to a list of Exchange objects
                finalBalances.map { Exchange(it.value, it.key, 0.0) }
            }
    }

    override suspend fun exchangeView(): Flow<List<Exchange>> =
        exchangeLocalDataSource.exchange().map {
            it.map { exchange ->
                exchange.toExchange()
            }
        }
}
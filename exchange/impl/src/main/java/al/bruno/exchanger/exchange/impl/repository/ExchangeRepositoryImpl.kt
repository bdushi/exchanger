package al.bruno.exchanger.exchange.impl.repository

import al.bruno.exchanger.data.network.ExchangeNetworkDataSource
import al.bruno.exchanger.exchange.api.domain.ExchangeRate
import al.bruno.exchanger.exchange.api.repository.ExchangeRepository
import al.bruno.exchanger.exchange.impl.ext.toExchange

class ExchangeRepositoryImpl(
    private val messagesLocalDataSource: ExchangeNetworkDataSource
) : ExchangeRepository {
    override suspend fun getMessages(): List<ExchangeRate> =
        messagesLocalDataSource
            .getExchangeRate()
            .map {
                it.toExchange()
            }
}
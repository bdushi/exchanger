package al.bruno.exchanger.exchange.impl.repository

import al.bruno.exchanger.data.local.ExchangeLocalDataSource
import al.bruno.exchanger.data.network.ExchangeNetworkDataSource
import al.bruno.exchanger.data.network.model.ExchangeRateResponse
import al.bruno.exchanger.exchange.api.domain.Exchange
import al.bruno.exchanger.exchange.api.domain.ExchangeRate
import al.bruno.exchanger.exchange.api.repository.ExchangeRepository
import al.bruno.exchanger.exchange.impl.ext.toExchange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExchangeRepositoryImpl(
    private val exchangeNetworkDataSource: ExchangeNetworkDataSource,
    private val exchangeLocalDataSource: ExchangeLocalDataSource,
) : ExchangeRepository {
    override suspend fun getMessages(): List<ExchangeRate> =
        exchangeNetworkDataSource
            .getExchangeRate()
            .map(ExchangeRateResponse::toExchange)

    override suspend fun exchange(): Flow<List<Exchange>> =
        exchangeLocalDataSource.exchange().map {
            it.map { exchange ->
                exchange.toExchange()
            }
        }
}
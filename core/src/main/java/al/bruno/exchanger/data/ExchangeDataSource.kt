package al.bruno.exchanger.data

import al.bruno.exchanger.data.local.ExchangeLocalDataSource
import al.bruno.exchanger.data.local.dao.ExchangeDao
import al.bruno.exchanger.data.local.model.Exchange
import al.bruno.exchanger.data.network.ExchangeNetworkDataSource
import al.bruno.exchanger.data.network.model.ExchangeRateResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

class ExchangeDataSource(
    private val httpClient: HttpClient,
    private val exchangeDao: ExchangeDao
) : ExchangeNetworkDataSource, ExchangeLocalDataSource {
    override suspend fun getExchangeRate(): List<ExchangeRateResponse> =
        httpClient
            .get("/tasks/api/currency-exchange-rates")
            .body<List<ExchangeRateResponse>>()

    override suspend fun exchange(): Flow<List<Exchange>> =
        exchangeDao.exchange()

}
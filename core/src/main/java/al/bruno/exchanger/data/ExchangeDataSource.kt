package al.bruno.exchanger.data

import al.bruno.exchanger.data.network.ExchangeNetworkDataSource
import al.bruno.exchanger.data.network.model.ExchangeRateResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ExchangeDataSource(
    private val httpClient: HttpClient
) : ExchangeNetworkDataSource {
    override suspend fun getExchangeRate(): List<ExchangeRateResponse> =
        httpClient
            .get("/tasks/api/currency-exchange-rates")
            .body<List<ExchangeRateResponse>>()
}
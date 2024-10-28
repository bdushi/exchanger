package al.bruno.exchanger.data

import al.bruno.exchanger.data.network.model.ExchangeRateResponse
import al.bruno.exchanger.data.network.ExchangeNetworkDataSource
import al.bruno.exchanger.data.network.service.ExchangeService

class ExchangeDataSource(
    private val exchangeService: ExchangeService
) : ExchangeNetworkDataSource {
    override suspend fun getExchangeRate(): List<ExchangeRateResponse>  {
        val response = exchangeService.exchange()
        return response
    }
}
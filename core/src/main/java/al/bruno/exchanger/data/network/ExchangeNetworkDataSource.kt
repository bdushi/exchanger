package al.bruno.exchanger.data.network

import al.bruno.exchanger.data.network.model.ExchangeRateResponse

interface ExchangeNetworkDataSource {
    suspend fun getExchangeRate(): List<ExchangeRateResponse>
}
package al.bruno.exchanger.data.network.service

import al.bruno.exchanger.data.network.model.ExchangeRateResponse
import retrofit2.http.GET

interface ExchangeService {
    @GET("/tasks/api/currency-exchange-rates")
    suspend fun exchange(): List<ExchangeRateResponse>
}

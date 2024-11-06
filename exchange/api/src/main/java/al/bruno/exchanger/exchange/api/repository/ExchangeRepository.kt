package al.bruno.exchanger.exchange.api.repository

import al.bruno.exchanger.exchange.api.domain.Exchange
import al.bruno.exchanger.exchange.api.domain.ExchangeRate
import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {
    suspend fun getMessages(): List<ExchangeRate>
    suspend fun exchange(): Flow<List<Exchange>>
}

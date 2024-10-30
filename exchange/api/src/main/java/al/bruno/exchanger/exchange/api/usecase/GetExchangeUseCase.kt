package al.bruno.exchanger.exchange.api.usecase

import al.bruno.exchanger.exchange.api.domain.Exchange
import kotlinx.coroutines.flow.Flow

interface GetExchangeUseCase {
    suspend operator fun invoke(): Flow<List<Exchange>>
}
package al.bruno.exchanger.exchange.impl.usecase

import al.bruno.exchanger.exchange.api.domain.Exchange
import al.bruno.exchanger.exchange.api.repository.ExchangeRepository
import al.bruno.exchanger.exchange.api.usecase.GetExchangeUseCase
import kotlinx.coroutines.flow.Flow

class GetExchangeUseCaseImpl(private val exchangeRepository: ExchangeRepository) :
    GetExchangeUseCase {
    override suspend fun invoke(): Flow<List<Exchange>> =
        exchangeRepository.exchange()
}
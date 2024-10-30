package al.bruno.exchanger.exchange.impl.usecase

import al.bruno.exchanger.common.core.Result
import al.bruno.exchanger.exchange.api.domain.ExchangeRate
import al.bruno.exchanger.exchange.api.repository.ExchangeRepository
import al.bruno.exchanger.exchange.api.usecase.GetExchangeRateUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetExchangeRateUseCaseImpl(private val exchangeRepository: ExchangeRepository) :
    GetExchangeRateUseCase {
    override operator fun invoke(): Flow<Result<List<ExchangeRate>>> = flow<Result<List<ExchangeRate>>> {
        emit(Result.Success(exchangeRepository.getMessages()))
    }.catch { exception ->
        emit(Result.Error("Failed to get contacts: ${exception.message}"))
    }
}
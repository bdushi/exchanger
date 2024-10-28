package al.bruno.exchanger.exchange.api.usecase

import al.bruno.exchanger.common.core.Result
import al.bruno.exchanger.exchange.api.domain.ExchangeRate
import kotlinx.coroutines.flow.Flow

interface GetExchangeRateUseCase {
    operator fun invoke(): Flow<Result<List<ExchangeRate>>>
}
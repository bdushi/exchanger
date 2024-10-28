package al.bruno.event.greet.message.api.usecase

import al.bruno.exchanger.exchange.api.domain.ExchangeRate
import kotlinx.coroutines.flow.Flow

interface FindMessageByIdUseCase {
    operator fun invoke(id: Long): Flow<Result<ExchangeRate>>
}
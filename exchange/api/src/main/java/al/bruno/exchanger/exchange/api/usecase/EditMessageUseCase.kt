package al.bruno.event.greet.message.api.usecase

import al.bruno.exchanger.exchange.api.domain.ExchangeRate

interface EditMessageUseCase {
    suspend operator fun invoke(exchangeRate: ExchangeRate)
}
package al.bruno.exchanger.currency.converter.impl.usecase

import al.bruno.exchanger.currency.converter.api.domain.Balance
import al.bruno.exchanger.currency.converter.api.repository.ConverterRepository
import al.bruno.exchanger.currency.converter.api.usecase.GetBalanceUseCase

class GetBalanceUseCaseImpl(private val converterRepository: ConverterRepository) : GetBalanceUseCase {
    override suspend fun invoke(): Balance = converterRepository.balance()
}
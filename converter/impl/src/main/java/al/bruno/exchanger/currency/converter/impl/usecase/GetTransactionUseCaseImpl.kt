package al.bruno.exchanger.currency.converter.impl.usecase

import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.repository.ConverterRepository
import al.bruno.exchanger.currency.converter.api.usecase.GetTransactionUseCase
import kotlinx.coroutines.flow.Flow

class GetTransactionUseCaseImpl(private val converterRepository: ConverterRepository) : GetTransactionUseCase {
    override fun invoke(): Flow<List<Transaction>> = converterRepository.transaction()
}
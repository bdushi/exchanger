package al.bruno.exchanger.currency.converter.impl.usecase

import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.repository.ConverterRepository
import al.bruno.exchanger.currency.converter.api.usecase.InsertTransactionUseCase

class InsertTransactionUseCaseImpl(private val converterRepository: ConverterRepository) :
    InsertTransactionUseCase {
    override suspend fun invoke(transaction: List<Transaction>) = converterRepository.insert(transaction)
}
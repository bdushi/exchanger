package al.bruno.exchanger.currency.converter.impl.usecase

import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.repository.TransactionRepository
import al.bruno.exchanger.currency.converter.api.usecase.InsertTransactionUseCase

class InsertTransactionUseCaseImpl(private val transactionRepository: TransactionRepository) :
    InsertTransactionUseCase {
    override suspend fun invoke(transaction: Transaction) {
        transactionRepository.insert(transaction)
    }
}
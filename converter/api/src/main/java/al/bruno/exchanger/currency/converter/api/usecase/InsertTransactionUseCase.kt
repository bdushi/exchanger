package al.bruno.exchanger.currency.converter.api.usecase

import al.bruno.exchanger.currency.converter.api.domain.Transaction

interface InsertTransactionUseCase {
    suspend operator fun invoke(transaction: Transaction)
}
package al.bruno.exchanger.currency.converter.api.usecase

import al.bruno.exchanger.common.core.Result
import al.bruno.exchanger.currency.converter.api.domain.NewTransaction
import al.bruno.exchanger.currency.converter.api.domain.Transaction

interface InsertTransactionUseCase {
    suspend operator fun invoke(newTransaction: NewTransaction): Result<List<Transaction>>
}
package al.bruno.exchanger.currency.converter.api.usecase

import al.bruno.exchanger.common.core.Result
import al.bruno.exchanger.currency.converter.api.domain.NewTransaction

interface InsertTransactionUseCase {
    suspend operator fun invoke(newTransaction: NewTransaction): Result<List<Long>>
}
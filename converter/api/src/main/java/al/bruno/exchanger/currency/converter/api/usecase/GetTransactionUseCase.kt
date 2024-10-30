package al.bruno.exchanger.currency.converter.api.usecase

import al.bruno.exchanger.currency.converter.api.domain.Transaction
import kotlinx.coroutines.flow.Flow

interface GetTransactionUseCase {
    operator fun invoke() : Flow<List<Transaction>>
}
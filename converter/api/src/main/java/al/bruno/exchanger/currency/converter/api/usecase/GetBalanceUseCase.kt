package al.bruno.exchanger.currency.converter.api.usecase

import al.bruno.exchanger.currency.converter.api.domain.Balance

interface GetBalanceUseCase {
    suspend operator fun invoke() : Balance
}
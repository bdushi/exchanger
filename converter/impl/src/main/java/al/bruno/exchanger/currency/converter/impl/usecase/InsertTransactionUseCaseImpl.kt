package al.bruno.exchanger.currency.converter.impl.usecase

import al.bruno.exchanger.common.core.Result
import al.bruno.exchanger.currency.converter.api.domain.Commission
import al.bruno.exchanger.currency.converter.api.domain.NewTransaction
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.TransactionType
import al.bruno.exchanger.currency.converter.api.repository.ConverterRepository
import al.bruno.exchanger.currency.converter.api.usecase.InsertTransactionUseCase
import java.time.LocalDate

class InsertTransactionUseCaseImpl(private val converterRepository: ConverterRepository) :
    InsertTransactionUseCase {
    override suspend fun invoke(newTransaction: NewTransaction): Result<List<Transaction>> {
        return try {
            val transactions = listOf(
                Transaction(
                    id = 0,
                    transactionType = TransactionType.RECEIVE,
                    value = newTransaction.fromAmount * newTransaction.rates,
                    balanceId = newTransaction.balanceId,
                    currency = newTransaction.currency,
                    rate = newTransaction.rates,
                    dateCreated = LocalDate.now(),
                    lastUpdated = LocalDate.now()
                ),
                Transaction(
                    id = 0,
                    transactionType = TransactionType.SELL,
                    value = newTransaction.fromAmount,
                    balanceId = newTransaction.balanceId,
                    currency = newTransaction.base,
                    rate = 1.0,
                    dateCreated = LocalDate.now(),
                    lastUpdated = LocalDate.now()
                )
            )
            Result.Success(converterRepository.insertTransactions(transactions))
        } catch (ex: Exception) {
            Result.Error(ex.message)
        }
    }
}
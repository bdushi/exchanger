package al.bruno.exchanger.currency.exchanger.impl

import al.bruno.exchanger.common.core.Result
import al.bruno.exchanger.currency.converter.api.domain.Commission
import al.bruno.exchanger.currency.converter.api.domain.NewTransaction
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.TransactionType
import al.bruno.exchanger.currency.converter.api.repository.ConverterRepository
import al.bruno.exchanger.currency.converter.api.usecase.InsertTransactionUseCase
import al.bruno.exchanger.currency.converter.impl.usecase.InsertTransactionUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class InsertTransactionUseCaseImplTest {

    @MockK
    private lateinit var converterRepository: ConverterRepository
    private lateinit var insertTransactionUseCase: InsertTransactionUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        insertTransactionUseCase = InsertTransactionUseCaseImpl(converterRepository)
    }

    @Test
    fun `invoke should insert transactions and return them`() = runTest {
        // Given
        val newTransaction = NewTransaction(
            fromAmount = 100.0,
            rates = 1.2,
            balanceId = 1,
            currency = "USD",
            base = "EUR"
        )

        val expectedTransactions = listOf(
            Transaction(
                id = 0,
                transactionType = TransactionType.RECEIVE,
                value = newTransaction.fromAmount * newTransaction.rates, // 100.0 * 1.2 = 120.0
                balanceId = newTransaction.balanceId,
                currency = newTransaction.currency,
                rate = newTransaction.rates,
                commission = Commission(),
                dateCreated = LocalDate.now(),
                lastUpdated = LocalDate.now()
            ),
            Transaction(
                id = 0,
                transactionType = TransactionType.SELL,
                value = newTransaction.fromAmount, // 100.0
                balanceId = newTransaction.balanceId,
                currency = newTransaction.base,
                rate = 1.0,
                commission = Commission(),
                dateCreated = LocalDate.now(),
                lastUpdated = LocalDate.now()
            )
        )

        // Mock repository call to return the expected transactions
        coEvery { converterRepository.insertTransactions(any()) } returns expectedTransactions

        // When
        val result = insertTransactionUseCase(newTransaction)

        // Then
        // Verify that the repository insert method was called with the correct transactions
        coVerify(exactly = 1) { converterRepository.insertTransactions(any()) }

        // Verify the result is the expected list of transactions
        assert(result is Result.Success)
        assertEquals(expectedTransactions, (result as Result.Success).data)
    }

    @Test
    fun `invoke should return Result Error when an exception is thrown`() = runTest {
        // Given
        val newTransaction = NewTransaction(
            fromAmount = 100.0,
            rates = 1.2,
            balanceId = 1,
            currency = "USD",
            base = "EUR"
        )

        // Mock repository to throw an exception
        coEvery { converterRepository.insertTransactions(any()) } throws Exception("Error occurred")

        // When
        val result = insertTransactionUseCase.invoke(newTransaction)

        // Then
        // Verify that the result is a Result.Error with the correct message
        assert(result is Result.Error)
        assertEquals("Error occurred", (result as Result.Error).error)
    }
}

package al.bruno.exchanger.currency.exchanger.impl

import al.bruno.exchanger.currency.converter.api.domain.Commission
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.TransactionType
import al.bruno.exchanger.currency.converter.api.repository.ConverterRepository
import al.bruno.exchanger.currency.converter.impl.ext.toTransactionEntity
import al.bruno.exchanger.currency.converter.impl.usecase.GetTransactionUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class GetTransactionUseCaseImplTest {

    private lateinit var getTransactionUseCase: GetTransactionUseCaseImpl
    @MockK
    private lateinit var converterRepository: ConverterRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getTransactionUseCase = GetTransactionUseCaseImpl(converterRepository)
    }

    @Test
    fun `invoke should return transaction flow from repository`() = runTest {
        val transactionEntities =
            listOf(
                Transaction(
                    id = 2,
                    transactionType = TransactionType.RECEIVE,
                    value = 1.0,
                    balanceId = 1,
                    commission = Commission(),
                    rate = .1,
                    currency = "EUR",
                    dateCreated = LocalDate.now(),
                    lastUpdated = LocalDate.now()
                ),
                Transaction(
                    id = 2,
                    transactionType = TransactionType.SELL,
                    value = 1.0,
                    balanceId = 1,
                    commission = Commission(),
                    rate = .1,
                    currency = "USD",
                    dateCreated = LocalDate.now(),
                    lastUpdated = LocalDate.now()
                )
            )
        // When
        coEvery { converterRepository.transaction() } returns flowOf(transactionEntities)

        // Then
        converterRepository.transaction().collect { result ->
            assertEquals(transactionEntities.size, result.size)
            result.forEachIndexed { index, transaction ->
                assertEquals(transactionEntities[index], transaction)
            }
        }
        // Verify
        /**
         * coVerify(exactly = 1) is a MockK verification function
         * that ensures a suspended function (coroutine) was called exactly the specified number of times.
         * In this case, it verifies that converterRepository.transaction() was called exactly once during the test.
         */
        coVerify(exactly = 1) { converterRepository.transaction() }
    }
}

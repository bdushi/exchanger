package al.bruno.exchanger.currency.exchanger.impl

import al.bruno.exchanger.currency.converter.api.domain.Balance
import al.bruno.exchanger.currency.converter.api.repository.ConverterRepository
import al.bruno.exchanger.currency.converter.api.usecase.GetBalanceUseCase
import al.bruno.exchanger.currency.converter.impl.usecase.GetBalanceUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class GetBalanceUseCaseImplTest {

    @MockK
    private lateinit var converterRepository: ConverterRepository

    private lateinit var getBalanceUseCase: GetBalanceUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getBalanceUseCase = GetBalanceUseCaseImpl(converterRepository)
    }

    @Test
    fun `invoke should return balance from repository`() = runTest {
        // Given
        val expectedBalance = Balance(
            id = 1,
            amount = 100.0,
            currency = "EUR",
            dateCreated = LocalDate.now(),
            lastUpdated = LocalDate.now()
        )

        // When
        coEvery { converterRepository.balance() } returns expectedBalance
        val result = getBalanceUseCase()

        // Then
        assertEquals(expectedBalance, result)
        assertEquals(expectedBalance.id, result.id)
        // This tells assertEquals that the two floating-point numbers are considered equal if their difference is smaller than the specified delta (in this case, 0.000001).
        assertEquals(expectedBalance.amount, result.amount, 0.000001)
        assertEquals(expectedBalance.currency, result.currency)
        assertEquals(expectedBalance.dateCreated, result.dateCreated)
        assertEquals(expectedBalance.lastUpdated, result.lastUpdated)

        coVerify(exactly = 1) { converterRepository.balance() }
    }
}
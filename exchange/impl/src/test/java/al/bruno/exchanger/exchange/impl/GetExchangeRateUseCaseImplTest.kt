package al.bruno.exchanger.exchange.impl

import al.bruno.exchanger.common.core.Result
import al.bruno.exchanger.exchange.api.domain.ExchangeRate
import al.bruno.exchanger.exchange.api.repository.ExchangeRepository
import al.bruno.exchanger.exchange.api.usecase.GetExchangeRateUseCase
import al.bruno.exchanger.exchange.impl.usecase.GetExchangeRateUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetExchangeRateUseCaseImplTest {

    @MockK
    private lateinit var exchangeRepository: ExchangeRepository
    private lateinit var getExchangeRateUseCase: GetExchangeRateUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getExchangeRateUseCase = GetExchangeRateUseCaseImpl(exchangeRepository)
    }


    @Test
    fun `invoke should emit exchange rates as Result Success`() = runTest {
        // Given
        val expectedExchangeRates = listOf(
            ExchangeRate(
                base = "USD",
                date = "",
                rates = mapOf(
                    "EUR" to 1.2,
                    "ALL" to 120.0
                )
            ),
            ExchangeRate(
                base = "EUR",
                date = "",
                rates = mapOf(
                    "USD" to 1.2,
                    "ALL" to 125.0
                )
            )
        )

        // Mock repository call to return the expected exchange rates
        coEvery { exchangeRepository.getExchangeRate() } returns expectedExchangeRates

        // When
        val flow = getExchangeRateUseCase.invoke()

        // Then
        flow.take(1).collect { result ->
            assert(result is Result.Success)
            assertEquals(expectedExchangeRates, (result as Result.Success).data)
        }

        // Verify that the repository method was called
        coVerify(exactly = 1) { exchangeRepository.getExchangeRate() }
    }

    @Test
    fun `invoke should emit Result Error when an exception is thrown`() = runTest {
        // Given
        val errorMessage = "Network error"

        // Mock repository to throw an exception
        coEvery { exchangeRepository.getExchangeRate() } throws Exception(errorMessage)

        // When
        val flow = getExchangeRateUseCase.invoke()

        // Then
        flow.take(1).collect { result ->
            assert(result is Result.Error)
            assertEquals("Failed to get contacts: $errorMessage", (result as Result.Error).error)
        }

        // Verify that the repository method was called
        coVerify(exactly = 1) { exchangeRepository.getExchangeRate() }
    }
}

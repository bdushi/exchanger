package al.bruno.exchanger.exchange.impl

import al.bruno.exchanger.exchange.api.domain.Exchange
import al.bruno.exchanger.exchange.api.repository.ExchangeRepository
import al.bruno.exchanger.exchange.api.usecase.GetExchangeRateUseCase
import al.bruno.exchanger.exchange.api.usecase.GetExchangeUseCase
import al.bruno.exchanger.exchange.impl.usecase.GetExchangeRateUseCaseImpl
import al.bruno.exchanger.exchange.impl.usecase.GetExchangeUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Before

class GetExchangeUseCaseImplTest {
    @MockK
    private lateinit var exchangeRepository: ExchangeRepository
    private lateinit var getExchangeUseCase: GetExchangeUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getExchangeUseCase = GetExchangeUseCaseImpl(exchangeRepository)
    }

    @Test
    fun `invoke should return a flow of exchange data from repository`() = runTest {
        // Given
        val expectedExchanges = listOf(
            Exchange(
                currency = "USD",
                balance = 100.0,
                commission = 120.0
            ),
            Exchange(
                currency = "EUR",
                balance = 000.0,
                commission = 120.0
            )
        )

        // Mock repository to return a Flow of exchange data
        coEvery { exchangeRepository.exchange() } returns flowOf(expectedExchanges)

        // When
        val flow = getExchangeUseCase.invoke()

        // Then
        flow.collect { result ->
            assertEquals(expectedExchanges, result)
        }

        // Verify that the repository method was called
        coVerify(exactly = 1) { exchangeRepository.exchange() }
    }
}

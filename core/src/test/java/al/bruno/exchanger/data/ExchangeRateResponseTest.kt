package al.bruno.exchanger.data

import al.bruno.exchanger.data.network.service.ExchangeService
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import okhttp3.MediaType.Companion.toMediaType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class ExchangeRateResponseTest {
    private lateinit var api: ExchangeService

    @Before
    fun setup() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://developers.paysera.com")
            .addConverterFactory(
                Json {
                    serializersModule = SerializersModule {
                        contextual(ExchangeRateResponseSerializer)
                    }
                }.asConverterFactory("application/json".toMediaType())
            )
            .build()

        api = retrofit.create(ExchangeService::class.java)
    }

    @Test
    fun `deserialize list of ExchangeRateResponse objects`() = runBlocking {
//        val json = Json {
//            serializersModule = SerializersModule {
//                contextual(ExchangeRateResponseSerializer)
//            }
//        }

//        val data = """
//            {
//            "base":"EUR",
//            "date":"2022-10-06",
//                "rates":{
//                    "AED":4.147043,
//                    "AFN":118.466773
//                }
//            }
//        """.trimIndent()
//
//        val list = """
//            [
//            {
//            "base":"EUR",
//            "date":"2022-10-06",
//                "rates":{
//                    "AED":4.147043,
//                    "AFN":118.466773
//                }
//            },
//            {
//            "base":"EUR",
//            "date":"2022-10-06",
//                "rates":{
//                    "AED":4.147043,
//                    "AFN":118.466773
//                }
//            }
//            ]
//        """.trimIndent()
//
//        json.decodeFromString(ExchangeRateResponseSerializer, data)
//
//        json.decodeFromString(ExchangeRateResponseSerializer, list)

        val result = api.exchange()
    }

    @Test
    fun `deserialize valid response`() = runBlocking {
        val result = api.exchange()
        assertEquals("USD", result[0].base)
        assertEquals("2024-10-01", result[0].date)
        assertEquals(0.85, result[0].rates["EUR"])
        assertEquals(0.75, result[0].rates["GBP"])
    }
}
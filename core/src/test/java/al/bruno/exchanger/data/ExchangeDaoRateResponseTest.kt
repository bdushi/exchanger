package al.bruno.exchanger.data

import al.bruno.exchanger.data.network.model.ExchangeRateResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ExchangeDaoRateResponseTest {
    private lateinit var httpClient: HttpClient

    @Before
    fun setup() {
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

        val result = httpClient
            .get("/tasks/api/currency-exchange-rates")
            .body<List<ExchangeRateResponse>>()
    }
}
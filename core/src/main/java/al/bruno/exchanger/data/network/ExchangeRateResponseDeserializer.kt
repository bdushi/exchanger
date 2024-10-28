package al.bruno.exchanger.data.network

import al.bruno.exchanger.data.network.model.ExchangeRateResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ExchangeRateResponseDeserializer: JsonDeserializer<List<ExchangeRateResponse>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): List<ExchangeRateResponse> {
        return when {
            json.isJsonObject -> {
                listOf(context.deserialize(json, ExchangeRateResponse::class.java))
            }
            json.isJsonArray -> {
                json.asJsonArray.map { element ->
                    context.deserialize(element, ExchangeRateResponse::class.java)
                }
            }
            else -> {
                emptyList()
            }
        }
    }
}
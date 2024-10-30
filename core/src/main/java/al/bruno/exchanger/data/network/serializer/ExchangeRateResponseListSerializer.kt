package al.bruno.exchanger.data.network.serializer

import al.bruno.exchanger.data.network.model.ExchangeRateResponse
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

object ExchangeRateResponseListSerializer : KSerializer<List<ExchangeRateResponse>> {
    private val singleSerializer = ExchangeRateResponse.serializer()
    private val listSerializer = ListSerializer(singleSerializer)

    override val descriptor: SerialDescriptor = listSerializer.descriptor

    override fun serialize(encoder: Encoder, value: List<ExchangeRateResponse>) {
        listSerializer.serialize(encoder, value)
    }

    override fun deserialize(decoder: Decoder): List<ExchangeRateResponse> {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("Expected JsonDecoder for ExchangeRateResponse")
        return when (val element: JsonElement = jsonDecoder.decodeJsonElement()) {
            is JsonObject -> listOf(jsonDecoder.json.decodeFromJsonElement(singleSerializer, element))
            is JsonArray -> jsonDecoder.json.decodeFromJsonElement(listSerializer, element)
            else -> throw SerializationException("Expected JsonObject or JsonArray but got ${element::class}")
        }
    }
}
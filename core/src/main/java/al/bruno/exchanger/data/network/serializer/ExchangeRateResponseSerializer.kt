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
import kotlinx.serialization.json.JsonObject

object ExchangeRateResponseSerializer : KSerializer<List<ExchangeRateResponse>> {
    private val listSerializer = ListSerializer(ExchangeRateResponse.serializer())
    private val singleSerializer = ExchangeRateResponse.serializer()

    override val descriptor: SerialDescriptor = listSerializer.descriptor

    override fun serialize(encoder: Encoder, value: List<ExchangeRateResponse>) {
        listSerializer.serialize(encoder, value)
    }

    override fun deserialize(decoder: Decoder): List<ExchangeRateResponse> {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("Expected JsonDecoder but got ${decoder::class}")

        return when (val element = jsonDecoder.decodeJsonElement()) {
            is JsonObject -> {
                try {
                    listOf(jsonDecoder.json.decodeFromJsonElement(singleSerializer, element))
                } catch (e: Exception) {
                    throw SerializationException("Failed to decode single object: ${e.message}")
                }
            }
            is JsonArray -> {
                try {
                    jsonDecoder.json.decodeFromJsonElement(listSerializer, element)
                } catch (e: Exception) {
                    throw SerializationException("Failed to decode array: ${e.message}")
                }
            }
            else -> throw SerializationException(
                "Expected JsonObject or JsonArray but got ${element::class}"
            )
        }
    }
}

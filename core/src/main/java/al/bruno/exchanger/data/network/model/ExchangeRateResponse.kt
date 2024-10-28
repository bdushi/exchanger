package al.bruno.exchanger.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateResponse(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
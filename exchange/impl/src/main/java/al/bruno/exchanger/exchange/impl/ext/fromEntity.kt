package al.bruno.exchanger.exchange.impl.ext

import al.bruno.exchanger.exchange.api.domain.ExchangeRate
import al.bruno.exchanger.data.network.model.ExchangeRateResponse

fun ExchangeRateResponse.toExchange() = ExchangeRate(
    base = base,
    date = date,
    rates = rates
)
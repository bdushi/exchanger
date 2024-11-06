package al.bruno.exchanger.exchange.impl.ext

import al.bruno.exchanger.data.network.model.ExchangeRateResponse
import al.bruno.exchanger.exchange.api.domain.Exchange
import al.bruno.exchanger.exchange.api.domain.ExchangeRate

fun ExchangeRateResponse.toExchange() = ExchangeRate(
    base = base,
    date = date,
    rates = rates
)

fun ExchangeEntity.toExchange() =
    Exchange(
        currency = currency,
        balance = totalValue,
        commission = commission,
    )
package al.bruno.exchanger.ui.exchange.ext

import al.bruno.exchanger.exchange.api.domain.ExchangeRate
import al.bruno.exchanger.ui.exchange.model.ExchangeRateUI


fun ExchangeRate.mapExchangeRateToUIModel() = ExchangeRateUI(
    base = base,
    date = date,
    rates = rates
)
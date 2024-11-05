package al.bruno.exchanger.ui.converter.ext

import al.bruno.exchanger.currency.converter.api.domain.Balance
import al.bruno.exchanger.exchange.api.domain.ExchangeRate
import al.bruno.exchanger.ui.converter.model.BalanceUI
import al.bruno.exchanger.ui.converter.model.ExchangeRateUI

fun ExchangeRate.mapExchangeRateToUIModel() = ExchangeRateUI(
    base = base,
    date = date,
    rates = rates
)

fun Balance.mapBalanceToUIModel() = BalanceUI(
    id = id,
    amount = amount,
    currency = currency
)
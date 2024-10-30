package al.bruno.exchanger.ui.exchange.ext

import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.Type
import al.bruno.exchanger.exchange.api.domain.Exchange
import al.bruno.exchanger.exchange.api.domain.ExchangeRate
import al.bruno.exchanger.ui.exchange.model.ExchangeRateUI
import al.bruno.exchanger.ui.exchange.model.ExchangeUI
import al.bruno.exchanger.ui.exchange.model.TransactionUI
import al.bruno.exchanger.ui.exchange.model.TypeUI


fun ExchangeRate.mapExchangeRateToUIModel() = ExchangeRateUI(
    base = base,
    date = date,
    rates = rates
)

fun Exchange.mapExchangeToUIModel() = ExchangeUI(
    balance = balance,
    currency = currency,
    commission = commission
)

fun Transaction.mapTransactionUIModel() = TransactionUI(
    id = id,
    currency = currency,
    commission = commission,
    type = type.mapTypeUIModel(),
    value = value,
)

fun Type.mapTypeUIModel() = TypeUI.valueOf(this.name)
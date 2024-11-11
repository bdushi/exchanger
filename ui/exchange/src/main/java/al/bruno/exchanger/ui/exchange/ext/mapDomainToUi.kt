package al.bruno.exchanger.ui.exchange.ext

import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.TransactionType
import al.bruno.exchanger.exchange.api.domain.Exchange
import al.bruno.exchanger.ui.exchange.model.ExchangeUI
import al.bruno.exchanger.ui.exchange.model.TransactionTypeUI
import al.bruno.exchanger.ui.exchange.model.TransactionUI

fun Exchange.mapExchangeToUIModel() = ExchangeUI(
    balance = balance,
    currency = currency,
)

fun Transaction.mapTransactionUIModel() = TransactionUI(
    id = id,
    currency = currency,
    type = transactionType.mapTransactionTypeUIModel(),
    value = value,
)

fun TransactionType.mapTransactionTypeUIModel() = TransactionTypeUI.valueOf(this.name)
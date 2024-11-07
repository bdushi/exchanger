package al.bruno.exchanger.ui.exchange.ext

import al.bruno.exchanger.currency.converter.api.domain.Commission
import al.bruno.exchanger.currency.converter.api.domain.CommissionType
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.TransactionType
import al.bruno.exchanger.exchange.api.domain.Exchange
import al.bruno.exchanger.ui.exchange.model.CommissionTypeUI
import al.bruno.exchanger.ui.exchange.model.CommissionUI
import al.bruno.exchanger.ui.exchange.model.ExchangeUI
import al.bruno.exchanger.ui.exchange.model.TransactionTypeUI
import al.bruno.exchanger.ui.exchange.model.TransactionUI

fun Exchange.mapExchangeToUIModel() = ExchangeUI(
    balance = balance,
    currency = currency,
    commission = commission
)

fun Transaction.mapTransactionUIModel() = TransactionUI(
    id = id,
    currency = currency,
    commission = commission.mapCommissionUIModel(),
    type = transactionType.mapTransactionTypeUIModel(),
    value = value,
)

fun Commission.mapCommissionUIModel() = CommissionUI(
    commissionType = commissionType.mapCommissionTypeUIModel(),
    commission = commission,
    commissionRate = commissionRate
)

fun CommissionType.mapCommissionTypeUIModel() = CommissionTypeUI.valueOf(this.name)

fun TransactionType.mapTransactionTypeUIModel() = TransactionTypeUI.valueOf(this.name)
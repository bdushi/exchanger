package al.bruno.exchanger.currency.converter.impl.ext

import al.bruno.exchanger.currency.converter.api.domain.Balance
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.Type


fun BalanceEntity.toBalance() =
    Balance(
        id = id,
        amount = amount,
        currency = currency,
        dateCreated = dateCreated,
        lastUpdated = lastUpdated
    )

fun TransactionEntity.toTransaction() = Transaction(
    id = id,
    value = value,
    type = type.toType(),
    balanceId = balanceId,
    commission = commission,
    currency = currency,
    dateCreated = dateCreated,
    lastUpdated = lastUpdated
)

fun TypeEntity.toType() = Type.valueOf(this.name)
package al.bruno.exchanger.currency.converter.impl.ext

import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.Type
import al.bruno.exchanger.currency.converter.api.domain.Balance


fun Transaction.toTransactionEntity() = TransactionEntity(
    id = id,
    value = value,
    type = type.toTypeEntity(),
    balanceId = balanceId,
    commission = commission,
    currency = currency,
    rate = rate,
    dateCreated = dateCreated,
    lastUpdated = lastUpdated
)

fun Type.toTypeEntity() = TypeEntity.valueOf(this.name)

fun Balance.toBalance() = BalanceEntity(
    id = id,
    amount = amount,
    currency = currency,
    dateCreated = dateCreated,
    lastUpdated = lastUpdated
)


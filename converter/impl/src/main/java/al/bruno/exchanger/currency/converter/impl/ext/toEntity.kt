package al.bruno.exchanger.currency.converter.impl.ext

import al.bruno.exchanger.currency.converter.api.domain.Balance
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.TransactionType

fun Transaction.toTransactionEntity() = TransactionEntity(
    id = id,
    value = value,
    transactionType = transactionType.toTypeEntity(),
    balanceId = balanceId,
    commission = commission,
    currency = currency,
    rate = rate,
    dateCreated = dateCreated,
    lastUpdated = lastUpdated
)

fun TransactionType.toTypeEntity() = TransactionTypeEntity.valueOf(this.name)

fun Balance.toBalance() = BalanceEntity(
    id = id,
    amount = amount,
    currency = currency,
    dateCreated = dateCreated,
    lastUpdated = lastUpdated
)


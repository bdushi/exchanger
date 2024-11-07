package al.bruno.exchanger.currency.converter.impl.ext

import al.bruno.exchanger.currency.converter.api.domain.Commission
import al.bruno.exchanger.currency.converter.api.domain.CommissionType
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.TransactionType

fun Transaction.toTransactionEntity() = TransactionEntity(
    id = id,
    value = value,
    transactionType = transactionType.toTypeEntity(),
    balanceId = balanceId,
    commission = commission.toCommissionEntity(),
    currency = currency,
    rate = rate,
    dateCreated = dateCreated,
    lastUpdated = lastUpdated
)

fun TransactionType.toTypeEntity() = TransactionTypeEntity.valueOf(this.name)

fun Commission.toCommissionEntity() = CommissionEntity(
    commissionType = commissionType.toCommissionTypeEntity(),
    commission = commission,
    commissionRate = commission
)

fun CommissionType.toCommissionTypeEntity() =
    CommissionTypeEntity.valueOf(this.name)


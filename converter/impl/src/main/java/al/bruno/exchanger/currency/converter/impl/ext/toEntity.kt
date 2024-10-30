package al.bruno.exchanger.currency.converter.impl.ext

import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.Type


fun Transaction.toTransactionEntity() = TransactionEntity(
    id = id,
    value = value,
    type = type.toTypeEntity(),
    balanceId = balanceId,
    commission = commission,
    createdDate = createdDate,
    update = update
)

fun Type.toTypeEntity() = TypeEntity.valueOf(this.name)


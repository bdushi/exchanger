package al.bruno.exchanger.currency.converter.impl.ext

import al.bruno.exchanger.currency.converter.api.domain.Balance
import al.bruno.exchanger.currency.converter.api.domain.ExchangeRule
import al.bruno.exchanger.currency.converter.api.domain.RuleType
import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.currency.converter.api.domain.TransactionType


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
    transactionType = transactionType.toType(),
    balanceId = balanceId,
    commission = commission,
    currency = currency,
    rate = rate,
    dateCreated = dateCreated,
    lastUpdated = lastUpdated
)

fun TransactionTypeEntity.toType() = TransactionType.valueOf(this.name)

fun ExchangeRuleEntity.toExchangeRuleEntity() = ExchangeRule(
    id = id,
    description = description,
    condition = condition,
    action = action.toRuleType(),
    value = value,
    dateCreated = dateCreated,
    lastUpdated = lastUpdated
)

fun RuleTypeEntity.toRuleType() = RuleType.valueOf(this.name)
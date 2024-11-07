package al.bruno.exchanger.currency.converter.impl.ext

import al.bruno.exchanger.currency.converter.api.domain.Balance
import al.bruno.exchanger.currency.converter.api.domain.Commission
import al.bruno.exchanger.currency.converter.api.domain.CommissionType
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
    commission = commission.toCommission(),
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

fun CommissionEntity.toCommission() = Commission(
    commissionType = commissionType.toCommissionType(),
    commission = commission,
    commissionRate = commissionRate
)

fun CommissionTypeEntity.toCommissionType() = CommissionType.valueOf(this.name)

fun RuleTypeEntity.toRuleType() = RuleType.valueOf(this.name)
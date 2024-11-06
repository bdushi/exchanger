package al.bruno.exchanger.data.local

import al.bruno.exchanger.data.local.model.ExchangeRule

interface TransactionRuleLocalDataSource {
    suspend fun getExchangeRules(): List<ExchangeRule>
}
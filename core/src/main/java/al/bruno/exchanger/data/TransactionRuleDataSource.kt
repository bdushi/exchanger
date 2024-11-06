package al.bruno.exchanger.data

import al.bruno.exchanger.data.local.TransactionRuleLocalDataSource
import al.bruno.exchanger.data.local.dao.ExchangeRuleDao

class TransactionRuleDataSource(private val exchangeRuleDao: ExchangeRuleDao) :
    TransactionRuleLocalDataSource {
    override suspend fun getExchangeRules() =
        exchangeRuleDao.getExchangeRules()
}
package al.bruno.exchanger.data

import al.bruno.exchanger.data.local.BalanceLocalDataSource
import al.bruno.exchanger.data.local.dao.BalanceDao
import al.bruno.exchanger.data.local.model.Balance

class BalanceDataSource(private val balanceDao: BalanceDao): BalanceLocalDataSource {
    override suspend fun balance(): Balance = balanceDao.balance()
}

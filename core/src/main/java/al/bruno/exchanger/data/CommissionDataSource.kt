package al.bruno.exchanger.data

import al.bruno.exchanger.data.local.CommissionLocalDataSource
import al.bruno.exchanger.data.local.dao.CommissionDao
import al.bruno.exchanger.data.local.model.Commission

class CommissionDataSource(
    private val commissionDao: CommissionDao
) : CommissionLocalDataSource {
    override suspend fun insert(commissions: List<Commission>): List<Long> =
        commissionDao.insert(commissions)
}
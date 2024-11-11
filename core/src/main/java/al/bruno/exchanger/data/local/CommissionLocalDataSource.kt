package al.bruno.exchanger.data.local

import al.bruno.exchanger.data.local.model.Commission

interface CommissionLocalDataSource {
    suspend fun insert(commissions: List<Commission>): List<Long>
}
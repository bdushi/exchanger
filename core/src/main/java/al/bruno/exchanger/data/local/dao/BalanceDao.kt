package al.bruno.exchanger.data.local.dao

import al.bruno.exchanger.data.local.model.Balance
import androidx.room.Dao
import androidx.room.Query

@Dao
interface BalanceDao {
    @Query(
        """
        SELECT 
            b.id,
            COALESCE(SUM(b.amount), 0) - COALESCE(`transaction`.total_transaction, 0) AS amount,
            b.currency,
            b.dateCreated,
            b.lastUpdated
        FROM 
            Balance b
        LEFT JOIN
            (SELECT balanceId, SUM(value) AS total_transaction
             FROM `Transaction`
             WHERE transactionType = 'SELL'
             GROUP BY balanceId) AS `transaction` ON b.id = `transaction`.balanceId
        GROUP BY
            b.currency
    """
    )
    suspend fun balance() : Balance
}
package al.bruno.exchanger.data.local.dao

import al.bruno.exchanger.data.local.model.Balance
import androidx.room.Dao
import androidx.room.Query

@Dao
interface BalanceDao {
    @Query("""
        SELECT 
        b.id,
        COALESCE(SUM(b.amount), 0) +
            COALESCE(SUM(CASE WHEN t.type = 'RECEIVE' THEN t.value ELSE 0 END), 0) - 
            COALESCE(SUM(CASE WHEN t.type = 'SELL' THEN 1/t.rate * t.value ELSE 0 END), 0) AS amount,
        b.currency,
        b.dateCreated,
        b.lastUpdated
FROM 
    Balance b
LEFT JOIN
    `Transaction` t ON b.id = t.balanceId
GROUP BY 
    b.currency
    """)
    suspend fun balance() : Balance
}
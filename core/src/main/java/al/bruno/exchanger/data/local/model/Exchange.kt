package al.bruno.exchanger.data.local.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(
    """
    SELECT 
        b.currency AS currency,
        COALESCE(SUM(b.amount), 0) - COALESCE(`transaction`.total_transaction, 0) AS total_value,
        0 AS commission
    FROM 
        Balance b
    LEFT JOIN
        (SELECT balanceId, SUM(value) AS total_transaction
         FROM `Transaction`
         WHERE transactionType = 'SELL'
         GROUP BY balanceId) AS `transaction` ON b.id = `transaction`.balanceId
    GROUP BY
        b.currency
    UNION 
-- Second part: Get balances for currencies present only in the Transaction table
    SELECT 
        t.currency AS currency,
        SUM(t.value) AS total_value,
        0 AS commission
    FROM 
        `Transaction` t
    WHERE 
        t.transactionType = 'RECEIVE'
    GROUP BY 
        t.currency;
"""
)
data class Exchange(
    @ColumnInfo(name = "total_value")
    val totalValue: Double,
    val currency: String,
    val commission: Double,
)


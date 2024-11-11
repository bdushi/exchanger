package al.bruno.exchanger.data.local.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(
    """
    SELECT 
        b.currency AS currency,
        COALESCE(SUM(b.amount), 0) - COALESCE(`transaction`.`transaction`, 0) AS balance
    FROM 
        Balance b
    LEFT JOIN
        (SELECT 
             balanceId, 
             SUM(`transaction`) AS `transaction`
         FROM `Transaction`
         WHERE transactionType = 'SELL'
         GROUP BY balanceId) AS `transaction` ON b.id = `transaction`.balanceId
    GROUP BY
        b.currency
    UNION 
-- Second part: Get balances for currencies present only in the Transaction table
    SELECT 
        t.currency AS currency,
        SUM(t.`transaction`) AS balance
    FROM 
        `Transaction` t
    WHERE 
        t.transactionType = 'RECEIVE'
    GROUP BY 
        t.currency;
"""
)
data class Exchange(
    val balance: Double,
    val currency: String
)


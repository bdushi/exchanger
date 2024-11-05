package al.bruno.exchanger.data.local.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView("""
-- First part: Get balances for currencies present in the Balance table
SELECT 
    b.currency AS currency,
    COALESCE(SUM(b.amount), 0) + 
    COALESCE(SUM(CASE WHEN t.type = 'RECEIVE' THEN t.value ELSE 0 END), 0) - 
    COALESCE(SUM(CASE WHEN t.type = 'SELL' THEN 1/t.rate * t.value ELSE 0 END), 0) AS total_value,
    0 AS commission
FROM 
    Balance b
LEFT JOIN
    `Transaction` t ON b.id = t.balanceId
GROUP BY 
    b.currency

UNION ALL

-- Second part: Get balances for currencies present only in the Transaction table
SELECT 
    t.currency AS currency,
    ABS(
        ROUND(
            COALESCE(SUM(CASE WHEN t.type = 'RECEIVE' THEN t.value ELSE 0 END), 0) - 
            COALESCE(SUM(CASE WHEN t.type = 'SELL' THEN 1/t.rate * t.value ELSE 0 END),0), 
        4)
    ) AS total_value,
    0 AS commission
FROM 
    `Transaction` t
LEFT JOIN 
    Balance b ON b.id = t.balanceId
GROUP BY 
    t.currency;
""")
data class Exchange(
    @ColumnInfo(name = "total_value")
    val totalValue: Double,
    val currency: String,
    val commission: Double,
)


package al.bruno.exchanger.data.local.dao

import al.bruno.exchanger.data.local.model.ExchangeRule
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ExchangeRuleDao {
    @Query("SELECT * FROM ExchangeRule WHERE enable = :enable")
    suspend fun getExchangeRules(enable: Boolean = true): List<ExchangeRule>
}
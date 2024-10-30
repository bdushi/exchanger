package al.bruno.exchanger.data.local.dao

import al.bruno.exchanger.data.local.model.Balance
import androidx.room.Dao
import androidx.room.Query

@Dao
interface BalanceDao {
    @Query("SELECT * FROM Balance")
    suspend fun balance() : Balance
}
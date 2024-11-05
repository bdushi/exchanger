package al.bruno.exchanger.data.local.dao

import al.bruno.exchanger.data.local.model.Exchange
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExchangeDao {
    @Query("SELECT * FROM Exchange")
    fun exchange() : Flow<List<Exchange>>
}

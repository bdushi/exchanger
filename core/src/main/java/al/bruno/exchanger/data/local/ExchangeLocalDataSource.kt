package al.bruno.exchanger.data.local

import al.bruno.exchanger.data.local.model.Exchange
import kotlinx.coroutines.flow.Flow

interface ExchangeLocalDataSource {
    suspend fun exchange() : Flow<List<Exchange>>
}
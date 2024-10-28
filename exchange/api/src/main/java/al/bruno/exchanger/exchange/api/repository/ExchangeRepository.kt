package al.bruno.exchanger.exchange.api.repository

import al.bruno.exchanger.exchange.api.domain.ExchangeRate

interface ExchangeRepository {
    suspend fun getMessages(): List<ExchangeRate>
//    suspend fun findMessageById(id: Long): ExchangeRate
//    suspend fun deleteMessage(id: Long)
//    suspend fun createMessage(exchangeRate: ExchangeRate)
//    suspend fun updateMessage(exchangeRate: ExchangeRate)
//    suspend fun addToFavourite(isFavourite: Boolean, id: Long)
//    suspend fun updateOrder(id: Long)
}

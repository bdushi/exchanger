package al.bruno.exchanger.exchange.impl.repository

import al.bruno.exchanger.data.network.ExchangeNetworkDataSource
import al.bruno.exchanger.exchange.api.domain.ExchangeRate
import al.bruno.exchanger.exchange.api.repository.ExchangeRepository
import al.bruno.exchanger.exchange.impl.ext.toExchange

class ExchangeRepositoryImpl(
    private val messagesLocalDataSource: ExchangeNetworkDataSource
) : ExchangeRepository {
    override suspend fun getMessages(): List<ExchangeRate> =
        messagesLocalDataSource
            .getExchangeRate()
            .map {
                it.toExchange()
            }

//    override suspend fun findMessageById(id: Long) =
//        messagesLocalDataSource
//            .findMessageById(id)
//            .toMessage()
//
//    override suspend fun deleteMessage(id: Long) =
//        messagesLocalDataSource.deleteMessage(id)
//
//    override suspend fun createMessage(message: Message) =
//        messagesLocalDataSource.createMessage(message.toEntity())
//
//    override suspend fun updateMessage(message: Message) =
//        messagesLocalDataSource.updateMessage(message.toEntity())
//
//    override suspend fun addToFavourite(isFavourite: Boolean, id: Long) =
//        messagesLocalDataSource.addToFavourite(isFavourite = isFavourite, id = id)
//
//    override suspend fun updateOrder(id: Long) =
//        messagesLocalDataSource.updateOrder(id = id)
}
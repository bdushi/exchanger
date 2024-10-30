package al.bruno.exchanger.data.local

import al.bruno.exchanger.data.local.converter.DateTimeConverter
import al.bruno.exchanger.data.local.converter.TransactionConverter
import al.bruno.exchanger.data.local.dao.BalanceDao
import al.bruno.exchanger.data.local.dao.ExchangeDao
import al.bruno.exchanger.data.local.dao.TransactionDao
import al.bruno.exchanger.data.local.model.Balance
import al.bruno.exchanger.data.local.model.Transaction
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Balance::class, Transaction::class],
    version = 1
)
@TypeConverters(value = [DateTimeConverter::class, TransactionConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun balanceDao(): BalanceDao
    abstract fun exchangeDao(): ExchangeDao
    abstract fun transactionDao(): TransactionDao
}
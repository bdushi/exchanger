package al.bruno.exchanger.data.local

import al.bruno.exchanger.data.local.converter.DateTimeConverter
import al.bruno.exchanger.data.local.converter.EnumConverter
import al.bruno.exchanger.data.local.dao.BalanceDao
import al.bruno.exchanger.data.local.dao.ExchangeDao
import al.bruno.exchanger.data.local.dao.TransactionDao
import al.bruno.exchanger.data.local.dao.ExchangeRuleDao
import al.bruno.exchanger.data.local.model.Balance
import al.bruno.exchanger.data.local.model.Exchange
import al.bruno.exchanger.data.local.model.Transaction
import al.bruno.exchanger.data.local.model.ExchangeRule
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Balance::class, Transaction::class, ExchangeRule::class],
    views = [Exchange::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(value = [DateTimeConverter::class, EnumConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun balanceDao(): BalanceDao
    abstract fun transactionDao(): TransactionDao
    abstract fun exchangeDao(): ExchangeDao
    abstract fun transactionRuleDao(): ExchangeRuleDao
}
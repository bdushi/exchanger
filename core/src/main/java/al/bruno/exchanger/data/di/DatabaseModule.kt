package al.bruno.exchanger.data.di

import al.bruno.exchanger.data.local.AppDatabase
import al.bruno.exchanger.data.local.dao.BalanceDao
import al.bruno.exchanger.data.local.dao.ExchangeDao
import al.bruno.exchanger.data.local.dao.TransactionDao
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/*
INSERT INTO Balance (amount, currency, dateCreated, lastUpdated)
VALUES (1000.0, 'EUR', '2024-10-30', '2024-10-30');

INSERT INTO Balance (amount, currency, dateCreated, lastUpdated)
VALUES (1000.0, 'EUR', DATE('now'), DATE('now'));
*/

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "exchanger"
        )
            .createFromAsset("exchanger.db")
            .build()
    }

    single<TransactionDao> {
        get<AppDatabase>().transactionDao()
    }
    single<BalanceDao> {
        get<AppDatabase>().balanceDao()
    }
    single<ExchangeDao> {
        get<AppDatabase>().exchangeDao()
    }
}

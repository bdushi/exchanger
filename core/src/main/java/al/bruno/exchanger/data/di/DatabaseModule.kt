package al.bruno.exchanger.data.di

import al.bruno.exchanger.data.local.AppDatabase
import al.bruno.exchanger.data.local.dao.TransactionDao
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "exchanger"
        ).build()
    }

    single<TransactionDao> {
        get<AppDatabase>().transactionDao()
    }
}

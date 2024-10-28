package al.bruno.exchanger.data.di

import al.bruno.exchanger.data.local.AppDatabase
import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> { roomDatabase(androidContext()) }
}

fun roomDatabase(context: Context) = Room.databaseBuilder(
    context,
    AppDatabase::class.java, "database-name"
).build()
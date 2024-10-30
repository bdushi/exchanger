package al.bruno.exchanger.data.di

import al.bruno.exchanger.data.BalanceDataSource
import al.bruno.exchanger.data.ExchangeDataSource
import al.bruno.exchanger.data.TransactionDataSource
import al.bruno.exchanger.data.local.BalanceLocalDataSource
import al.bruno.exchanger.data.local.TransactionLocalDataSource
import al.bruno.exchanger.data.network.ExchangeNetworkDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<ExchangeNetworkDataSource> {
        ExchangeDataSource(get())
    }

    single<TransactionLocalDataSource> {
        TransactionDataSource(get())
    }

    single<BalanceLocalDataSource> {
        BalanceDataSource(get())
    }
}
package al.bruno.exchanger.data.di

import al.bruno.exchanger.data.ExchangeDataSource
import al.bruno.exchanger.data.network.ExchangeNetworkDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<ExchangeNetworkDataSource> {
        ExchangeDataSource(get())
    }
}
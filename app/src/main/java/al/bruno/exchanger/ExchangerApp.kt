package al.bruno.exchanger

import al.bruno.exchanger.data.di.dataSourceModule
import al.bruno.exchanger.data.di.databaseModule
import al.bruno.exchanger.data.di.httpClient
import al.bruno.exchanger.exchange.impl.di.exchangeModule
import al.bruno.exchanger.ui.converter.di.converterUIModule
import al.bruno.exchanger.ui.exchange.di.exchangeUIModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ExchangerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@ExchangerApp)
            // Load modules
            modules(
                listOf(
                    httpClient,
                    exchangeModule,
                    exchangeUIModule,
                    converterUIModule,
                    databaseModule,
                    dataSourceModule,
                )
            )
        }
    }
}
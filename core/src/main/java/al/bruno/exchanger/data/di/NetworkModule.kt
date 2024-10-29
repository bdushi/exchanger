package al.bruno.exchanger.data.di

import al.bruno.exchanger.data.BuildConfig
import al.bruno.exchanger.data.network.serializer.ExchangeRateResponseDeserializer
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.gson.gson
import org.koin.dsl.module


val httpClient = module {
    single {
        HttpClient(CIO) {
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.HOST
                }
            }
            install(Logging) {
                level = LogLevel.BODY
                logger = Logger.ANDROID
            }
            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                    disableHtmlEscaping()
                    registerTypeAdapter(
                        List::class.java,
                        ExchangeRateResponseDeserializer()
                    )
                }
            }
        }
    }
}
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
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
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
                /*Json {
                    serializersModule = SerializersModule {
                        contextual(ExchangeRateResponseListSerializer) // Register the custom serializer
                    }
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                }*/
                gson {
                    setPrettyPrinting()
                    disableHtmlEscaping()
                    registerTypeAdapter(
                        List::class.java,
                        ExchangeRateResponseDeserializer()
                    )
                }
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}
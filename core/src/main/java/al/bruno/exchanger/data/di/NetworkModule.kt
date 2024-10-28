package al.bruno.exchanger.data.di

import al.bruno.exchanger.data.BuildConfig
import al.bruno.exchanger.data.network.ExchangeRateResponseDeserializer
import al.bruno.exchanger.data.network.model.ExchangeRateResponse
import al.bruno.exchanger.data.network.service.ExchangeService
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.HOST) // Make sure this is properly set in your BuildConfig
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY // Log all HTTP requests/responses
                    })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(get())) // Use the injected Gson instance
            // Uncomment this if you switch to Kotlin Serialization
            // .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}

val gsonModule = module {
    single {
        GsonBuilder()
            .registerTypeAdapter(
                List::class.java,
                ExchangeRateResponseDeserializer()
            )
            .create()
    }
}

//val jsonModule = module {
//    single {
//        Json {
//            ignoreUnknownKeys = true
//            prettyPrint = true
//            isLenient = true
//            serializersModule = SerializersModule {
//                polymorphic(List::class) {
//                    ExchangeRateResponseSerializer
//                }
//            }
//        }
//    }
//}

val jsonModule = module {
    single {
        GsonBuilder()
            .registerTypeAdapter(
                object : TypeToken<List<ExchangeRateResponse>>() {}.type,
                ExchangeRateResponseDeserializer()
            )
            .create()
    }
}

val serviceModule = module {
    single<ExchangeService> { get<Retrofit>().create(ExchangeService::class.java) }
}



package al.bruno.exchanger.exchange.impl.di

import al.bruno.exchanger.exchange.api.repository.ExchangeRepository
import al.bruno.exchanger.exchange.impl.usecase.GetExchangeRateUseCaseImpl
import al.bruno.exchanger.exchange.api.usecase.GetExchangeRateUseCase
import al.bruno.exchanger.exchange.api.usecase.GetExchangeUseCase
import al.bruno.exchanger.exchange.impl.repository.ExchangeRepositoryImpl
import al.bruno.exchanger.exchange.impl.usecase.GetExchangeUseCaseImpl
import org.koin.dsl.module

val exchangeModule = module {
    single<GetExchangeRateUseCase> { GetExchangeRateUseCaseImpl(get()) }
    single<GetExchangeUseCase> { GetExchangeUseCaseImpl(get()) }
    single<ExchangeRepository> { ExchangeRepositoryImpl(get(), get(), get(), get()) }
}
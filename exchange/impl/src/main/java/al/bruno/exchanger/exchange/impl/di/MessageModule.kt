package al.bruno.exchanger.exchange.impl.di

import al.bruno.exchanger.exchange.api.repository.ExchangeRepository
import al.bruno.exchanger.exchange.impl.usecase.GetExchangeRateUseCaseImpl
import al.bruno.exchanger.exchange.api.usecase.GetExchangeRateUseCase
import al.bruno.exchanger.exchange.impl.repository.ExchangeRepositoryImpl
import org.koin.dsl.module

val exchangeModule = module {
    single<GetExchangeRateUseCase> { GetExchangeRateUseCaseImpl(get()) }
    single<ExchangeRepository> { ExchangeRepositoryImpl(get()) }
}
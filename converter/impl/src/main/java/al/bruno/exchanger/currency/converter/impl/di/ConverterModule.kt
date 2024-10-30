package al.bruno.exchanger.currency.converter.impl.di

import al.bruno.exchanger.currency.converter.api.repository.ConverterRepository
import al.bruno.exchanger.currency.converter.api.usecase.GetBalanceUseCase
import al.bruno.exchanger.currency.converter.api.usecase.GetTransactionUseCase
import al.bruno.exchanger.currency.converter.api.usecase.InsertTransactionUseCase
import al.bruno.exchanger.currency.converter.impl.repository.ConverterRepositoryImpl
import al.bruno.exchanger.currency.converter.impl.usecase.GetBalanceUseCaseImpl
import al.bruno.exchanger.currency.converter.impl.usecase.GetTransactionUseCaseImpl
import al.bruno.exchanger.currency.converter.impl.usecase.InsertTransactionUseCaseImpl
import org.koin.dsl.module

val converterModule = module {
    single<InsertTransactionUseCase> { InsertTransactionUseCaseImpl(get()) }
    single<GetBalanceUseCase> { GetBalanceUseCaseImpl(get()) }
    single<GetTransactionUseCase> { GetTransactionUseCaseImpl(get()) }

    single<ConverterRepository> { ConverterRepositoryImpl(get(), get()) }
}
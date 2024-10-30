package al.bruno.exchanger.currency.converter.impl.di

import al.bruno.exchanger.currency.converter.api.repository.TransactionRepository
import al.bruno.exchanger.currency.converter.api.usecase.InsertTransactionUseCase
import al.bruno.exchanger.currency.converter.impl.repository.TransactionRepositoryImpl
import al.bruno.exchanger.currency.converter.impl.usecase.InsertTransactionUseCaseImpl
import org.koin.dsl.module

val converterModule = module {
    single<InsertTransactionUseCase> { InsertTransactionUseCaseImpl(get()) }
    single<TransactionRepository> { TransactionRepositoryImpl(get()) }
}
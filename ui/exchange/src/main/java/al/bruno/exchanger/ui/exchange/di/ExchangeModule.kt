package al.bruno.exchanger.ui.exchange.di

import al.bruno.exchanger.ui.exchange.ui.ExchangeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val exchangeUIModule = module {
    viewModel<ExchangeViewModel> { ExchangeViewModel(get()) }
}
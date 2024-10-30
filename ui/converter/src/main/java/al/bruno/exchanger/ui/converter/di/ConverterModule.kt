package al.bruno.exchanger.ui.converter.di

import al.bruno.exchanger.ui.converter.ui.ConverterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val converterUIModule = module{
    viewModel<ConverterViewModel> { ConverterViewModel(get(), get(), get()) }
}
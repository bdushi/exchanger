package al.bruno.exchanger.ui.converter.ui

sealed class ConverterIntent {
    data object GetExchangeRate : ConverterIntent()
}
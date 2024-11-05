package al.bruno.exchanger.ui.converter.model

import al.bruno.exchanger.ui.foundation.arch.State

// UI state
data class ConversionState(
    val fromValue: String = "",
    val toValue: String = "",
    val fromRate: ExchangeRateUI? = null,
    val toRate: RateUI? = null,
    val availableRates: List<RateUI> = emptyList(),
    val transactionUI: State<Long> = State.Loading,
    val balanceUI: State<BalanceUI> = State.Loading,
    val exchangeRateUI: State<List<ExchangeRateUI>> = State.Loading,
)
package al.bruno.exchanger.ui.exchange.ui

import al.bruno.exchanger.ui.foundation.arch.UiEffect

sealed class Effect : UiEffect {
    data object ShowToast : Effect()
}
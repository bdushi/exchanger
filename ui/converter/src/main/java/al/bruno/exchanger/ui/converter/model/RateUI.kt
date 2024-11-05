package al.bruno.exchanger.ui.converter.model

import al.bruno.exchanger.common.core.formatToFourDecimals
import al.bruno.exchanger.common.core.stringToDouble

data class RateUI(
    val currency: String,
    val rates: Double
) {
    override fun toString() = currency
}

fun RateUI.calculateConvertedRate(from: String): String {
    return if (from.isEmpty() || from.isBlank()) {
        ""
    } else {
        (from.stringToDouble() * rates).formatToFourDecimals()
    }
}

fun RateUI?.calculateInverseConvertedRate(to: String): String {
    return if (to.isEmpty() || to.isBlank()) {
        ""
    } else {
        val rate = if(this?.rates != null) (1 / rates) * to.stringToDouble() else 1.0
        rate.formatToFourDecimals()
    }
}
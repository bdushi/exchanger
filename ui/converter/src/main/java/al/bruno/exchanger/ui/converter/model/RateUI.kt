package al.bruno.exchanger.ui.converter.model

import al.bruno.exchanger.common.core.formatToFourDecimals

data class RateUI(
    val currency: String,
    val rates: Double
) {
    override fun toString() = currency
}

fun RateUI.calculateConvertedRate(from: String): String {
    return if (from.isEmpty()) {
        ""
    } else {
        (from.toDouble() * rates).formatToFourDecimals()
    }
}

fun RateUI?.calculateInverseConvertedRate(to: String): String {
    return if (to.isEmpty()) {
        "1.0"
    } else {
        val rate = if(this?.rates != null) (1 / rates) * to.toDouble() else 1.0
        rate.formatToFourDecimals()
    }
}
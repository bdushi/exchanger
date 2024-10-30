package al.bruno.exchanger.common.core

import java.text.DecimalFormat

fun Double.formatToFourDecimals(): String {
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(this)
}
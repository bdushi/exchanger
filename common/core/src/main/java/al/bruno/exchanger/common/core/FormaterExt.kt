package al.bruno.exchanger.common.core

import java.text.DecimalFormat

fun Double.formatToDecimals(): String =
    DecimalFormat("#.##")
        .format(this)
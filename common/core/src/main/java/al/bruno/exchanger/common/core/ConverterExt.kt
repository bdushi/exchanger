package al.bruno.exchanger.common.core

fun String.stringToDouble(defaultValue: Double = 1.0) = try {
    this.toDouble()
} catch (e: NumberFormatException) {
    defaultValue
}
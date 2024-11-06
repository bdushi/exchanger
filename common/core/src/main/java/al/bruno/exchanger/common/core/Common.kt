package al.bruno.exchanger.common.core

fun buildExchangeRateText(
    baseCurrency: String?,
    targetCurrency: String?,
    rate: Double? = 1.0
): String {
    if (baseCurrency == null || targetCurrency == null) return ""
    return "1 $baseCurrency = ${rate?.formatToDecimals()} $targetCurrency"
}
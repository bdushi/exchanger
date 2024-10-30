package al.bruno.exchanger.ui.converter.model

data class RateUI(
    val currency: String,
    val rates: Double
) {
    override fun toString() = currency
}
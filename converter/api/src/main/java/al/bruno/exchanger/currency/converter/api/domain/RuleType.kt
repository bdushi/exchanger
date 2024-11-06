package al.bruno.exchanger.currency.converter.api.domain

enum class RuleType {
    COMMISSION,        // Standard commission rules
    REWARD,            // Reward after specific conditions
    DISCOUNT,          // Discount on exchanges
    FREE_EXCHANGE,     // For rules like "every tenth exchange is free
    FREE_UP_TO_LIMIT,  // For rules like "exchanges up to 200 Euros are free
}
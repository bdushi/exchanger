
# Exchange Rules Engine

This project implements an Exchange Rules Engine, utilizing Factory and Decorator design patterns to support flexible and dynamic rule application on exchange transactions. The engine allows various rules to be defined and applied based on transaction conditions, such as commission, reward, discounts, and free exchanges.

## Overview

The rules engine applies different types of rules based on specific conditions for exchange transactions. Each rule type (like `COMMISSION`, `REWARD`, `DISCOUNT`, etc.) is implemented as a strategy, allowing the conditions to be easily modified or extended. Using this setup, we can configure conditions directly in the code or via a database of rules, making it scalable and adaptable.

## Design Patterns

- **Factory Pattern**: The `ExchangeRuleStrategyFactory` class is responsible for creating instances of different rule strategies based on the action type. This encapsulation makes it easy to add new rules by defining them as separate classes without modifying the factory logic.
  
- **Decorator Pattern**: Each rule strategy accepts a condition as a lambda, allowing flexible application of rule conditions at runtime. This pattern allows additional rules or conditions to be layered in dynamically.

## Implementation

1. **Factory**:
   - `ExchangeRuleStrategyFactory` creates instances of rule strategies based on the rule type and provides conditions as lambdas. 

2. **Strategies**:
   - Each strategy implements the `ExchangeRuleStrategy` interface, with rule-specific logic for applying transaction modifications.

3. **Conditions**:
   - Conditions for applying rules are provided as lambda expressions, allowing easy customization without modifying the strategy classes.

## Code Structure

- `ExchangeRuleStrategyFactory`: Factory that provides rule strategies based on the action type.
- `ExchangeRuleStrategy`: Interface for all rule strategies.
- `CommissionRule`, `RewardRule`, etc.: Implementations of `ExchangeRuleStrategy` for each rule type.
  
### Example Rule Strategy Usage

```kotlin
val factory = ExchangeRuleStrategyFactory()
val ruleStrategy = factory.getRuleStrategy(RuleType.COMMISSION, transactionCount)

val updatedTransaction = ruleStrategy.applyRule(transaction, rule)
```

## Adding New Rules

To add a new rule:
1. Define a new `RuleType`.
2. Create a new class implementing `ExchangeRuleStrategy` with the rule logic.
3. Add the new rule to `ExchangeRuleStrategyFactory`.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

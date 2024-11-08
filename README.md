# Currency Exchanger App

## Overview

This is a Currency Exchanger application built in Kotlin for Android. The app is designed following **Clean Architecture** principles with modularization. It utilizes **Ktor** for networking, **Koin** for Dependency Injection, **Android Rome** for local database management, and **Jetpack Compose** for the UI. **Unit Testing** has been implemented for the UseCase layer using **MockK** to ensure business logic accuracy.

## Technologies Used

- **Kotlin**: Primary programming language.
- **Jetpack Compose**: For building the UI.
- **Koin**: For Dependency Injection.
- **Ktor**: For handling network requests to fetch currency exchange rates.
- **Android Rome**: For managing local storage of user data and balances.
- **MockK**: For mocking dependencies in unit tests.
- **Clean Architecture**: Used to structure the project with clear separation between layers and modules.
- **Unit Testing**: Unit tests are implemented to ensure that the UseCase logic works as expected.

## Project Structure

The project is organized into several modules, each responsible for a specific part of the application. The following modules are included:

- **:app**: The main application module that ties everything together.
- **:core**: Core functionality shared across the app.
- **:exchange:impl**: Implementation of the currency exchange functionality.
- **:exchange:api**: API layer for fetching exchange rates from the network.
- **:converter:impl**: Implementation of the currency conversion logic.
- **:converter:api**: API interface for conversion logic.
- **:common:core**: Common utilities and base classes.
- **:ui:exchange**: UI layer for the exchange screen.
- **:ui:foundation**: Basic UI components and foundation code.
- **:ui:converter**: UI layer for the currency conversion screen.

## How to Run

In order to connect to the API, you'll need to specify the host in the `local.properties` file. Add the following line to your `local.properties` file:

   ```properties
   HOST = developers.paysera.com
   ```

## Unit Testing

Unit tests have been implemented for the **UseCase** layer, which handles the core business logic of currency conversion and balance management. The tests use **MockK** to mock dependencies and ensure the UseCase works as expected.

To run the unit tests, use the following command in Android Studio:

- Go to **Run > Run Tests**.
- Select the test configuration for your UseCase tests.

## Architecture

### Clean Architecture

The app follows **Clean Architecture** with the following layers:

- **Presentation Layer**: Contains the UI components using **Compose** that interact with the ViewModel.
- **Domain Layer**: Contains the UseCase, which holds the business logic for currency conversion, balance management, and commission calculation.
- **Data Layer**: Handles API interactions (via Ktor) to fetch currency exchange rates and persists the data in a local database (using Android Rome).
- **Dependency Injection**: **Koin** is used to manage dependencies across the app, ensuring the app's modules remain loosely coupled and maintainable.

### Modularization

The project is split into multiple modules for better maintainability and scalability. Each module is designed to have a single responsibility and can be easily extended in the future.

## Future Improvements

- **Adding ViewModel Testing**: In addition to UseCase testing, ViewModel testing can be implemented to ensure that UI-related logic, such as managing state and handling user input, works as expected. This will improve test coverage and ensure that the ViewModel interacts correctly with the UseCase layer.

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

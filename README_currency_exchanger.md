
# Currency Exchanger App

## Overview

This is a Currency Exchanger application built in Kotlin for Android. The application allows the user to convert between multiple currencies, with an initial balance of 1000 Euros (EUR). The conversion is powered by a public Currency Exchange Rate API, and the user can convert between any supported currency. The app features commission-free exchanges for the first five conversions and a 0.7% commission fee for subsequent conversions.

This project follows **Clean Architecture** principles with modularization, and uses **Ktor** for networking, **Koin** for Dependency Injection, **Android Rome** for local database management, and **Jetpack Compose** for the UI. **Unit Testing** is implemented for the UseCase layer using **MockK** to ensure business logic accuracy.

## Features

- **Currency Conversion**: Users can select the currency to sell and buy, input an amount, and convert it. 
- **Commission**: First five exchanges are free, after that, a 0.7% commission is applied.
- **Balance Display**: The user's balance is updated after each conversion and is displayed in the UI.
- **Sync with API**: Currency exchange rates are synchronized every 5 seconds via the public API.
- **Modularization**: The code is modularized for maintainability and future expansion.
- **Unit Testing**: Unit tests are implemented for the UseCase layer to ensure that the business logic works as expected.

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
   HOST = domain.com
   ```

   This is required by the **Google Secret Plugin**, which fetches the secret configurations for the app.

- Run the app on an emulator or a physical device.

   - Make sure you have the correct configurations for your API access.
   - Ensure your local properties file is configured properly to access the host.

- Once the app is built and running, you should be able to start converting currencies and testing the functionality.

## Unit Testing

Unit tests have been implemented for the **UseCase** layer, which handles the core business logic of currency conversion and balance management. The tests use **MockK** to mock dependencies and ensure the UseCase works as expected.

To run the unit tests, use the following command in Android Studio:

- Go to **Run > Run Tests**.
- Select the test configuration for your UseCase tests.

## Commission Logic

- **First five exchanges**: Free of charge.
- **After five exchanges**: 0.7% commission is charged on the converted amount.
- The commission is deducted separately from the source and destination currency balances.

Example:
- **Input**: Sell 100 EUR to buy USD.
- **Conversion**: 100 EUR -> 110.30 USD (Exchange rate: 1 EUR = 1.1030 USD).
- **Commission Fee**: 0.70 EUR (0.7% of 100 EUR).
- **Final Balances**: 
  - 1000 EUR -> 1000 - 100 - 0.70 = 899.30 EUR.
  - 0 USD -> 110.30 USD.

## Architecture

### Clean Architecture

The app follows **Clean Architecture** with the following layers:

- **Presentation Layer**: This contains the UI components (activities, fragments, composables) that interact with the ViewModel.
- **Domain Layer**: This contains the UseCase, which holds the business logic for currency conversion, balance management, and commission calculation.
- **Data Layer**: This handles the API interactions (via Ktor) to fetch currency exchange rates and persists the data in a local database (using Android Rome).
- **Dependency Injection**: **Koin** is used to manage dependencies across the app, ensuring the app's modules remain loosely coupled and maintainable.

### Modularization

The project is split into multiple modules for better maintainability and scalability. Each module is designed to have a single responsibility and can be easily extended in the future.

## Challenges

- **Synchronization of Exchange Rates**: Synchronizing the exchange rates every 5 seconds was challenging, but was successfully handled by using Ktor’s periodic fetching mechanism.
- **Commission Logic**: Implementing the commission logic in a flexible way was important for future extensibility, and the modular design allows easy changes to commission rules.
- **UI Design**: Although not exactly identical to the Zeplin design, the app’s UI was crafted using Jetpack Compose, which allowed for dynamic and flexible UI construction.

## Future Improvements

- **More Commission Rules**: Additional flexible commission rules can be added easily, such as a limit on free exchanges based on a specific amount of currency.
- **Currency Support**: New currencies can be added by simply modifying the API response and adding them to the conversion logic.

## Time Spent

- **Initial Setup & Architecture**: 8 hours
- **UI Implementation**: 6 hours
- **API Integration & Networking**: 4 hours
- **Commission Logic & UseCase**: 5 hours
- **Unit Testing**: 3 hours
- **Bug Fixing & Final Adjustments**: 2 hours

**Total Time Spent**: 28 hours

---

Feel free to open an issue or contribute to the project if you have any suggestions or improvements!

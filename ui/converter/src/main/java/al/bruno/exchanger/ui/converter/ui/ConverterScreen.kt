package al.bruno.exchanger.ui.converter.ui

import al.bruno.exchanger.common.core.formatToDecimals
import al.bruno.exchanger.ui.foundation.R
import al.bruno.exchanger.ui.foundation.arch.State
import al.bruno.exchanger.ui.foundation.component.ErrorContentComponent
import al.bruno.exchanger.ui.foundation.component.ExchangeTopBarWithBackButton
import al.bruno.exchanger.ui.foundation.component.LoadingContentComponent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewExchangeScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    converterViewModel: ConverterViewModel = koinViewModel()
) {
    val uiState by converterViewModel.uiState.collectAsState()

    val openAlertDialogError = remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            ExchangeTopBarWithBackButton(navigateUp)
        }
    ) {
        Column(
            modifier
                .padding(it)
                .padding(8.dp)
        ) {
            when (val exchangeRateUIState = uiState.exchangeRateUI) {
                is State.Error -> {
                    ErrorContentComponent(
                        errorMessages = stringResource(R.string.error_message),
                        errorButton = stringResource(R.string.retry),
                        onRetry = {
                            converterViewModel.processIntent(Event.GetExchangeRate)
                        }
                    )
                }
                is State.Loading -> {
                    LoadingContentComponent()
                }
                is State.Success -> {
                    ExchangeContent(
                        uiState = uiState,
                        exchangeRates = exchangeRateUIState.data,
                        onFromValueChange = { value ->
                            converterViewModel.onFromValueChange(value)
                        },
                        onToValueChange = { value ->
                            converterViewModel.onToValueChange(value)
                        },
                        onFromCurrencySelected = { selectedRate ->
                            converterViewModel.onFromCurrencySelected(selectedRate)
                        },
                        onToCurrencySelected = { selectedRate ->
                            converterViewModel.onToCurrencySelected(selectedRate)
                        },
                        onSubmit = { balance ->
                            converterViewModel.insert(balance)
                        },
                        onRetry = {
                            converterViewModel.balance()
                        }
                    )
                }
            }
            when (uiState.transactionUI) {
                is State.Error -> {
                    ErrorContentComponent(
                        errorMessages = stringResource(R.string.error_message),
                        errorButton = stringResource(R.string.retry),
                        onRetry = {

                        }
                    )
                }

                is State.Loading -> {

                }

                is State.Success -> {
                    openAlertDialogError.value = false
                    navigateUp.invoke()
                }
            }
        }
    }
}

fun buildExchangeRateText(
    baseCurrency: String?,
    targetCurrency: String?,
    rate: Double? = 1.0
): String {
    if (baseCurrency == null || targetCurrency == null) return ""
    return "1 $baseCurrency = ${rate?.formatToDecimals()} $targetCurrency"
}
package al.bruno.exchanger.ui.converter.ui

import al.bruno.exchanger.ui.foundation.R
import al.bruno.exchanger.ui.foundation.arch.State
import al.bruno.exchanger.ui.foundation.component.ErrorContentComponent
import al.bruno.exchanger.ui.foundation.component.ExchangeTopBarWithBackButton
import al.bruno.exchanger.ui.foundation.component.LoadingContentComponent
import al.bruno.exchanger.ui.foundation.theme.ExchangerTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
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


            when (val transactions = uiState.transactionUI) {
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
                    val sell = transactions.data.first()
                    val receive = transactions.data.last()
                    BasicAlertDialog(
                        onDismissRequest = {
                            openAlertDialogError.value = false
                        }
                    ) {
                        ExchangeDialogComponent(
                            sell = sell,
                            receive = receive,
                            base = uiState.fromRate?.base,
                            onClick = {
                                openAlertDialogError.value = true
                                navigateUp.invoke()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExchangerTheme {
        NewExchangeScreen(navigateUp = {

        })
    }
}
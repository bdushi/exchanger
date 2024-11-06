package al.bruno.exchanger.ui.converter.ui

import al.bruno.exchanger.common.core.buildExchangeRateText
import al.bruno.exchanger.common.core.stringToDouble
import al.bruno.exchanger.ui.converter.model.BalanceUI
import al.bruno.exchanger.ui.converter.model.ConversionState
import al.bruno.exchanger.ui.converter.model.ExchangeRateUI
import al.bruno.exchanger.ui.converter.model.RateUI
import al.bruno.exchanger.ui.foundation.R
import al.bruno.exchanger.ui.foundation.arch.State
import al.bruno.exchanger.ui.foundation.component.CurrencySelectedMenu
import al.bruno.exchanger.ui.foundation.component.ErrorContentComponent
import al.bruno.exchanger.ui.foundation.component.LoadingContentComponent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ExchangeContent(
    uiState: ConversionState,
    exchangeRates: List<ExchangeRateUI>,
    onFromValueChange: (String) -> Unit,
    onToValueChange: (String) -> Unit,
    onFromCurrencySelected: (ExchangeRateUI) -> Unit,
    onToCurrencySelected: (RateUI) -> Unit,
    onSubmit: (balance: BalanceUI) -> Unit,
    onRetry: () -> Unit,
) {
    val (isEnabled, setEnabled) = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CurrencySelectedMenu(
            modifier = Modifier.fillMaxWidth(),
            items = exchangeRates,
            label = stringResource(R.string.from_label),
            onItemSelected = onFromCurrencySelected,
            onValueChange = onFromValueChange,
            value = uiState.fromValue,
            placeholder = stringResource(R.string.value_placeholder),
            defaultText = stringResource(R.string.default_select_item),
            rate = buildExchangeRateText(
                baseCurrency = uiState.fromRate?.base,
                targetCurrency = uiState.toRate?.currency,
                rate = uiState.toRate?.rates
            ),
            enabled = isEnabled
        )

        CurrencySelectedMenu(
            modifier = Modifier.fillMaxWidth(),
            items = uiState.availableRates,
            label = stringResource(R.string.to_label),
            onItemSelected = onToCurrencySelected,
            onValueChange = onToValueChange,
            value = uiState.toValue,
            placeholder = stringResource(R.string.value_placeholder),
            defaultText = stringResource(R.string.default_select_item),
            rate = buildExchangeRateText(
                baseCurrency = uiState.toRate?.currency,
                targetCurrency = uiState.fromRate?.base,
                rate = if(uiState.toRate?.rates != null) 1 / uiState.toRate.rates else 1.0
            ),
            enabled = isEnabled
        )
        when(val balance = uiState.balanceUI) {
            is State.Error -> {
                ErrorContentComponent(
                    errorMessages = stringResource(R.string.error_message),
                    errorButton = stringResource(R.string.retry),
                    onRetry = onRetry
                )
            }
            is State.Loading -> {
                LoadingContentComponent()
            }
            is State.Success -> {
                if (balance.data.amount > 0) {
                    setEnabled(false)
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            onSubmit(balance.data)
                        },
                        enabled = balance.data.amount >= uiState.fromValue.stringToDouble(0.0) && uiState.toRate != null && uiState.toValue.isNotEmpty() && uiState.fromValue.isNotEmpty()
                    ) {
                        Text(
                            text = stringResource(R.string.submit),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                } else {
                    setEnabled(true)
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.out_of_balance),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
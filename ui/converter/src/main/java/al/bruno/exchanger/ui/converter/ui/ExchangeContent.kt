package al.bruno.exchanger.ui.converter.ui

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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    onSubmit: (balance: BalanceUI) -> Unit
) {
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
            )
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
            )
        )
        when(val balance = uiState.balanceUI) {
            is State.Error -> {
                ErrorContentComponent(
                    errorMessages = stringResource(R.string.error_message),
                    errorButton = stringResource(R.string.retry),
                    onRetry = {
                        // TODO
                    }
                )
            }
            is State.Loading -> {
                LoadingContentComponent()
            }
            is State.Success -> {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        onSubmit(balance.data)
                    },
                    enabled = balance.data.amount >= 0 && uiState.toRate != null
                ) {
                    Text(
                        text = stringResource(R.string.submit),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
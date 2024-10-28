package al.bruno.exchanger.ui.exchange.ui

import al.bruno.exchanger.ui.foundation.R
import al.bruno.exchanger.ui.foundation.theme.ExchangerTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel


@Composable
fun ExchangeScreen(
    modifier: Modifier = Modifier,
    exchangeViewModel: ExchangeViewModel = koinViewModel()
) {
    exchangeViewModel.processIntent(Event.GetExchange)
    val exchangeRate by exchangeViewModel.exchangeRate.collectAsState()
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.my_balances)
            )

            LazyRow {

            }
        }
        Column {
            Text(
                text = stringResource(id = R.string.currency_exchanger)
            )

            LazyColumn {

            }
        }
    }

    /**
     * when (exchangeRate) {
     *         State.Loading -> {
     *             Text(
     *                 modifier = modifier,
     *                 text = "Loading"
     *             )
     *         }
     *
     *         is State.Error -> {
     *             val exchange = (exchangeRate as State.Error)
     *             Text(
     *                 modifier = modifier,
     *                 text = exchange.error!!
     *             )
     *
     *         }
     *
     *         is State.Success -> {
     *             val exchange = (exchangeRate as State.Success<ExchangeRateUI>)
     *             Text(
     *                 modifier = modifier,
     *                 text = exchange.data.base
     *             )
     *         }
     *     }
     */
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExchangerTheme {
        ExchangeScreen()
    }
}
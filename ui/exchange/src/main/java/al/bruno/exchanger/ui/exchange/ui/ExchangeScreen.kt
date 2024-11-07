package al.bruno.exchanger.ui.exchange.ui

import al.bruno.exchanger.ui.foundation.theme.ExchangerTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExchangeScreen(
    modifier: Modifier = Modifier,
    exchangeViewModel: ExchangeViewModel = koinViewModel()
) {
    exchangeViewModel.processIntent(Event.Exchange)
    exchangeViewModel.processIntent(Event.Transaction)

    val exchange by exchangeViewModel.exchange.collectAsState()
    val transaction by exchangeViewModel.transaction.collectAsState()

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        CurrencyExchangeComponent(exchange = exchange)
        TransactionExchangeComponent(transaction = transaction)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExchangerTheme {
        ExchangeScreen()
    }
}
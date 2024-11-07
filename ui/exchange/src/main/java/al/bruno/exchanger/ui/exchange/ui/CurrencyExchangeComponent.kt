package al.bruno.exchanger.ui.exchange.ui

import al.bruno.exchanger.ui.exchange.model.ExchangeUI
import al.bruno.exchanger.ui.foundation.R
import al.bruno.exchanger.ui.foundation.arch.State
import al.bruno.exchanger.ui.foundation.component.ErrorMessageComponent
import al.bruno.exchanger.ui.foundation.component.LoadingContentComponent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CurrencyExchangeComponent(
    exchange: State<List<ExchangeUI>>
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = stringResource(id = R.string.my_balances),
                fontWeight = FontWeight.Bold
            )

            when (exchange) {
                is State.Error -> {
                    ErrorMessageComponent(
                        modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 16.dp, end = 8.dp),
                        text = stringResource(R.string.error_exchange_message)
                    )
                }

                is State.Loading -> {
                    LoadingContentComponent()
                }

                is State.Success -> {
                    LazyRow(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(exchange.data.size) { balance ->
                            ExchangeItem(exchange = exchange.data[balance])
                        }
                    }
                }
            }
        }
    }
}
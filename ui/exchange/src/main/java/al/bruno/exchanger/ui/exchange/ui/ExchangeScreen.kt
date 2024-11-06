package al.bruno.exchanger.ui.exchange.ui

import al.bruno.exchanger.common.core.formatToDecimals
import al.bruno.exchanger.ui.exchange.model.TransactionTypeUI.RECEIVE
import al.bruno.exchanger.ui.exchange.model.TransactionTypeUI.SELL
import al.bruno.exchanger.ui.foundation.R
import al.bruno.exchanger.ui.foundation.arch.State
import al.bruno.exchanger.ui.foundation.component.ErrorContentComponent
import al.bruno.exchanger.ui.foundation.component.LoadingContentComponent
import al.bruno.exchanger.ui.foundation.theme.ExchangerTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel


@Composable
fun ExchangeScreen(
    modifier: Modifier = Modifier,
    exchangeViewModel: ExchangeViewModel = koinViewModel()
) {
    exchangeViewModel.processIntent(Event.Exchange)

    val transaction by exchangeViewModel.transaction.collectAsState()
    val exchange by exchangeViewModel.exchange.collectAsState()

    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.my_balances)
            )
            when (val exchangeUI = exchange) {
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
                    LazyRow {
                        items(
                            count = exchangeUI.data.size,
                            itemContent = {
                                val data = exchangeUI.data[it]
                                Row(modifier = Modifier.padding(4.dp)) {
                                    Text(
                                        modifier = Modifier.padding(2.dp),
                                        text = data.balance.formatToDecimals()
                                    )
                                    Text(

                                        modifier = Modifier.padding(2.dp),
                                        text = data.currency
                                    )
                                }
                            }
                        )
                    }
                }
            }

        }
        Column {
            Text(
                text = stringResource(id = R.string.currency_exchanger)
            )
            when (val transactionUI = transaction) {
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
                    LazyColumn {
                        items(
                            count = transactionUI.data.size,
                            key = { index: Int -> transactionUI.data[index].id },
                            itemContent = {
                                val data = transactionUI.data[it]
                                Card(
                                    shape = RoundedCornerShape(2.dp),
                                    modifier = Modifier.padding(2.dp),
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth()
                                    ) {
                                        Row(
                                            modifier = Modifier.weight(1f),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start
                                        ) {
                                            when (data.type) {
                                                SELL -> {
                                                    Icon(
                                                        modifier = Modifier
                                                            .size(48.dp)
                                                            .padding(4.dp),
                                                        painter = painterResource(R.drawable.arrow_circle_up_24px),
                                                        tint = colorResource(R.color.sell),
                                                        contentDescription = "New Event Back Button"
                                                    )
                                                }

                                                RECEIVE -> {
                                                    Icon(
                                                        modifier = Modifier
                                                            .size(48.dp)
                                                            .padding(4.dp),
                                                        painter = painterResource(R.drawable.arrow_circle_down_24px),
                                                        tint = colorResource(R.color.receive),
                                                        contentDescription = "New Event Back Button"
                                                    )
                                                }
                                            }
                                            Text(
                                                modifier = Modifier.padding(4.dp),
                                                text = data.type.name
                                            )
                                        }

                                        Column(
                                            modifier = Modifier.weight(1f),
                                            horizontalAlignment = Alignment.End,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    modifier = Modifier.padding(4.dp),
                                                    text = data.value.formatToDecimals()
                                                )
                                                Text(
                                                    modifier = Modifier.padding(4.dp),
                                                    text = data.currency
                                                )
                                            }
                                        }
                                    }
                                }
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
        ExchangeScreen()
    }
}
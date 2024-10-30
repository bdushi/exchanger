package al.bruno.exchanger.ui.exchange.ui

import al.bruno.exchanger.common.core.formatToFourDecimals
import al.bruno.exchanger.ui.exchange.model.ExchangeUI
import al.bruno.exchanger.ui.exchange.model.TransactionUI
import al.bruno.exchanger.ui.exchange.model.TypeUI.RECEIVE
import al.bruno.exchanger.ui.exchange.model.TypeUI.SELL
import al.bruno.exchanger.ui.foundation.R
import al.bruno.exchanger.ui.foundation.arch.State
import al.bruno.exchanger.ui.foundation.theme.ExchangerTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
            when (exchange) {
                is State.Error -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        // Error message
                        Text(
                            text = "An error occurred. Please try again.",
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Red // Optional: color for the error message
                        )
                        Spacer(modifier = Modifier.height(16.dp)) // Space between message and button

                        // Retry button
                        TextButton(onClick = {
                            //
                        }) {
                            Text(text = "Retry")
                        }
                    }
                }

                is State.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White), // Optional: set background color
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator() // You can use LinearProgressIndicator() if preferred
                            Spacer(modifier = Modifier.height(16.dp)) // Space between progress and text
                            Text(text = "Loading...", fontWeight = FontWeight.SemiBold)
                        }
                    }
                }

                is State.Success -> {
                    val exchangeUI: List<ExchangeUI> =
                        (exchange as State.Success<List<ExchangeUI>>).data
                    LazyRow {
                        items(
                            count = exchangeUI.size,
                            itemContent = {
                                val data = exchangeUI[it]
                                Row(modifier = Modifier.padding(4.dp)) {
                                    Text(
                                        modifier = Modifier.padding(2.dp),
                                        text = data.balance.formatToFourDecimals()
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
            when (transaction) {
                is State.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            // Error message
                            Text(
                                text = "An error occurred. Please try again.",
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Red // Optional: color for the error message
                            )
                            Spacer(modifier = Modifier.height(16.dp)) // Space between message and button

                            // Retry button
                            TextButton(onClick = {
                                //
                            }) {
                                Text(text = "Retry")
                            }
                        }
                    }
                }

                is State.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White), // Optional: set background color
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator() // You can use LinearProgressIndicator() if preferred
                            Spacer(modifier = Modifier.height(16.dp)) // Space between progress and text
                            Text(text = "Loading...", fontWeight = FontWeight.SemiBold)
                        }
                    }
                }

                is State.Success -> {
                    val transactionUI: List<TransactionUI> =
                        (transaction as State.Success<List<TransactionUI>>).data
                    LazyColumn {
                        items(
                            count = transactionUI.size,
                            key = { index: Int -> transactionUI[index].id },
                            itemContent = {
                                val data = transactionUI[it]
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
                                                        tint = Color.Red,
                                                        contentDescription = "New Event Back Button"
                                                    )
                                                }

                                                RECEIVE -> {
                                                    Icon(
                                                        modifier = Modifier
                                                            .size(48.dp)
                                                            .padding(4.dp),
                                                        painter = painterResource(R.drawable.arrow_circle_down_24px),
                                                        tint = Color.Green,
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
                                                    text = data.value.formatToFourDecimals()
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
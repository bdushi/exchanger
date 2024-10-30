package al.bruno.exchanger.ui.converter.ui

import al.bruno.exchanger.common.core.formatToFourDecimals
import al.bruno.exchanger.ui.converter.model.ExchangeRateUI
import al.bruno.exchanger.ui.converter.model.RateUI
import al.bruno.exchanger.ui.foundation.R
import al.bruno.exchanger.ui.foundation.arch.State
import al.bruno.exchanger.ui.foundation.widget.CurrencySelectedMenu
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewExchangeScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    converterViewModel: ConverterViewModel = koinViewModel()
) {
    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }
    val (toExchangeRate, setToExchangeRate) = remember { mutableStateOf<RateUI?>(null) }
    val (fromExchangeRate, setFromExchangeRate) = remember { mutableStateOf<ExchangeRateUI?>(null) }
    val exchangeRate by converterViewModel.exchangeRate.collectAsState(State.Loading)
    var rates by remember { mutableStateOf<List<RateUI>>(emptyList()) }

    converterViewModel.processIntent(ConverterIntent.GetExchangeRate)
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = stringResource(id = R.string.name))
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "New Event Back Button"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier
                .padding(it)
                .padding(8.dp)
        ) {
            when(exchangeRate) {
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
                                converterViewModel.processIntent(ConverterIntent.GetExchangeRate)
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
                    val exchange = (exchangeRate as State.Success<List<ExchangeRateUI>>).data
                    CurrencySelectedMenu(
                        modifier = modifier
                            .padding(8.dp),
                        items = exchange,
                        label = "From",
                        onItemSelected = { selectedItem ->
                            rates = selectedItem.rates.map { rate -> RateUI(rate.key, rate.value) }
                            setFromExchangeRate(selectedItem)
                        },
                        onValueChange = { value ->
                            from = value
                            to = if(from.isEmpty()) "" else (if (toExchangeRate?.rates != null) from.toDouble() * toExchangeRate.rates else from.toDouble()).formatToFourDecimals()
                        },
                        value = from,
                        placeholder = "Value",
                        rate = "1 ${fromExchangeRate?.base} = ${toExchangeRate?.rates?.formatToFourDecimals()} ${toExchangeRate?.currency}"
                    )

                    Spacer(modifier.padding(8.dp))

                    CurrencySelectedMenu(
                        modifier = modifier
                            .padding(8.dp),
                        items = rates,
                        label = "To",
                        onItemSelected = { rateUI ->
                            setToExchangeRate(rateUI)
                            to = if(from.isEmpty()) "" else (if (toExchangeRate?.rates != null) from.toDouble() * toExchangeRate.rates else from.toDouble()).formatToFourDecimals()
                        },
                        onValueChange = { value ->
                            to = value
                            from = if(to.isEmpty()) "" else (if (toExchangeRate?.rates != null) ((1 / toExchangeRate.rates) * to.toDouble()) else 1 * to.toDouble()).formatToFourDecimals()
                        },
                        value = to,
                        placeholder = "Value",
                        rate = "1 ${toExchangeRate?.currency} = ${if (toExchangeRate?.rates != null) (1 / toExchangeRate.rates).formatToFourDecimals() else 1} ${fromExchangeRate?.base}"
                    )

                    Spacer(modifier.padding(8.dp))

                    Button(
                        modifier = modifier
                            .fillMaxWidth(),

                        onClick = {

                        }) {
                        Text(
                            modifier = modifier
                                .align(Alignment.CenterVertically),
                            fontWeight = FontWeight.SemiBold,
                            text = "Submit"
                        )
                    }
                }
            }
        }
    }
}
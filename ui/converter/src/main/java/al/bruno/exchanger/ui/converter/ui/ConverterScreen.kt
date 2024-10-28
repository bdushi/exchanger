package al.bruno.exchanger.ui.converter.ui

import al.bruno.exchanger.ui.converter.model.ExchangeRateUI
import al.bruno.exchanger.ui.foundation.R
import al.bruno.exchanger.ui.foundation.arch.State
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewExchangeScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    converterViewModel: ConverterViewModel = koinViewModel()
) {
    val exchangeRate by converterViewModel.exchangeRate.collectAsState()
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
            Modifier.padding(it)
        ) {
            when(exchangeRate) {
                is State.Error -> {
                    Text(text = (exchangeRate as State.Error).error!!)
                }
                is State.Loading -> {
                    Text(text = "Loading")
                }
                is State.Success -> {
                    val exchange = (exchangeRate as State.Success<List<ExchangeRateUI>>)
                    LazyColumn {
                        items(exchange.data.size) { index ->
                            Text(exchange.data[index].base)
                        }
                    }
                }
            }
        }
    }
}
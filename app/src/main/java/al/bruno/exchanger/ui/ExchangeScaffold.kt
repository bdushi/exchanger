package al.bruno.exchanger.ui

import al.bruno.exchanger.navigation.NavigationScreenActions
import al.bruno.exchanger.ui.exchange.ui.ExchangeScreen
import al.bruno.exchanger.ui.foundation.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeScaffold(
    navigationScreenActions: NavigationScreenActions
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = stringResource(id = R.string.name))
                })
        },
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = {
                    navigationScreenActions.newExchange()
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "fab"
                )
            }
        }
    ) { innerPadding ->
        ExchangeScreen(
            Modifier.padding(innerPadding)
        )
    }
}
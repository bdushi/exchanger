package al.bruno.exchanger.ui.exchange.ui

import al.bruno.exchanger.ui.exchange.model.TransactionUI
import al.bruno.exchanger.ui.foundation.R
import al.bruno.exchanger.ui.foundation.arch.State
import al.bruno.exchanger.ui.foundation.component.ErrorMessageComponent
import al.bruno.exchanger.ui.foundation.component.LoadingContentComponent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TransactionExchangeComponent(
    transaction: State<List<TransactionUI>>
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = stringResource(id = R.string.currency_exchanger),
                fontWeight = FontWeight.Bold
            )
            when (transaction) {
                is State.Error -> {
                    ErrorMessageComponent(
                        modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 16.dp, end = 8.dp),
                        text = stringResource(R.string.error_transaction_message)
                    )
                }

                is State.Loading -> {
                    LoadingContentComponent()
                }

                is State.Success -> {
                    LazyColumn(
                        modifier = Modifier.padding(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            count = transaction.data.size,
                        ) {
                            TransactionItem(
                                transaction = transaction.data[it]
                            )
                        }
                    }
                }
            }
        }
    }
}
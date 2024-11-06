package al.bruno.exchanger.ui.converter.ui

import al.bruno.exchanger.currency.converter.api.domain.Transaction
import al.bruno.exchanger.ui.foundation.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExchangeDialogComponent(
    onClick: () -> Unit,
    sell: Transaction,
    receive: Transaction,
    base: String?
) {
    Surface(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.large,
        shadowElevation = 6.dp,
        tonalElevation = 6.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.currency_converted),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.currency_converted_messages)
                    .format(
                        sell.value,
                        sell.currency,
                        receive.value,
                        receive.currency,
                        sell.commission,
                        base
                    ),
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )
            TextButton(
                onClick = onClick
            ) {
                Text(
                    text = stringResource(R.string.confirm),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}
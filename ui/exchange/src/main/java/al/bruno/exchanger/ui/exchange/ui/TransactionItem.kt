package al.bruno.exchanger.ui.exchange.ui

import al.bruno.exchanger.common.core.formatToDecimals
import al.bruno.exchanger.ui.exchange.model.CommissionTypeUI
import al.bruno.exchanger.ui.exchange.model.TransactionTypeUI
import al.bruno.exchanger.ui.exchange.model.TransactionUI
import al.bruno.exchanger.ui.foundation.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TransactionItem(
    transaction: TransactionUI
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(
                        id = when (transaction.type) {
                            TransactionTypeUI.SELL -> R.drawable.arrow_circle_up_24px
                            TransactionTypeUI.RECEIVE -> R.drawable.arrow_circle_down_24px
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .padding(4.dp),
                    tint = when (transaction.type) {
                        TransactionTypeUI.SELL -> colorResource(R.color.sell)
                        TransactionTypeUI.RECEIVE -> colorResource(R.color.receive)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = transaction.type.name,
                )
            }
            Column {
                Row(
                    modifier = Modifier.align(Alignment.End),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = transaction.value.formatToDecimals(),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = transaction.currency,
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = when (transaction.commission.commissionType) {
                        CommissionTypeUI.COMMISSION ->
                            stringResource(R.string.commission).format(
                            transaction.commission.commission,
                            transaction.currency
                        )

                        CommissionTypeUI.BONUS -> stringResource(R.string.bonus).format(
                            transaction.commission.commission,
                            transaction.currency
                        )

                        CommissionTypeUI.FREE -> stringResource(R.string.free_of_commission)
                    },
                    fontWeight = FontWeight.W200,
                    fontSize = 12.sp,
                )
            }
        }
    }
}
package al.bruno.exchanger.ui.foundation.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun <T> CurrencySelectedMenu(
    modifier: Modifier,
    value: String,
    items: List<T>,
    onItemSelected: (T) -> Unit,
    onValueChange: (String) -> Unit,
    onDoneActionClick: () -> Unit = {},
    label: String,
    placeholder: String,
    rate: String
) {
    val view = LocalView.current
    Card {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), // Make Row take full width
                verticalAlignment = Alignment.CenterVertically, // Center items vertically within the Row
            ) {
                ExposedDropdownMenu(
                    label = label,
                    modifier = modifier.weight(1F),
                    items = items,
                    onItemSelected = {
                        onItemSelected.invoke(it)
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = modifier
                        .weight(1F)
                ) {
                    TextField(
                        singleLine = true,
                        placeholder = {
                            Text(placeholder)
                        },
                        value = value,
                        onValueChange = onValueChange,
                        keyboardActions = KeyboardActions(onDone = {
                            view.clearFocus()
                            onDoneActionClick()
                        }),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number,
                        )
                    )
                    Text(
                        fontWeight = FontWeight.W200,
                        text = rate
                    )
                }
            }
        }
    }
}
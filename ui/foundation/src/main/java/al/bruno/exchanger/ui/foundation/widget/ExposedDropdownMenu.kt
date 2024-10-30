package al.bruno.exchanger.ui.foundation.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> ExposedDropdownMenu(
    modifier: Modifier,
    label: String,
    items: List<T>,
    onItemSelected: (T) -> Unit,
) {
    val (selectedItem, setSelectedItem) = remember { mutableStateOf(items.firstOrNull()) }
    val (expanded, setExpanded) = remember { mutableStateOf(false) }
    selectedItem?.let {
        onItemSelected.invoke(it)
    }
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { setExpanded(it) }
    ) {
        Column {
            Text(label)
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = selectedItem?.toString()
                        ?: "Select an item", // Default text if no item is selected
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .menuAnchor(MenuAnchorType.PrimaryEditable)
                )
                IconButton(onClick = {
                    setExpanded(!expanded)
                }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = if (expanded) "Collapse Dropdown" else "Expand Dropdown"
                    )
                }
            }
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                setExpanded(false)
            },
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable)
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    contentPadding = PaddingValues(2.dp),
                    text = {
                        Text(
                            modifier = modifier.padding(start = 4.dp),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            text = item.toString()
                        )
                    },
                    onClick = {
                        setSelectedItem(item) // Update selected item first
                        onItemSelected(item)   // Notify the selected item
                        setExpanded(false)
                    }
                )
            }
        }
    }
}

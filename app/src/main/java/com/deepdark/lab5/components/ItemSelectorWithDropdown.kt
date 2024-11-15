package com.deepdark.lab5.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deepdark.lab5.data.ElectricitySystemItem

@Composable
fun ItemSelectorWithDropdown(
    item: ElectricitySystemItem,
    count: Int,
    onCountChange: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Text(item.name, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(
            value = count.toString(),
            onValueChange = { newCount ->
                onCountChange(newCount.toIntOrNull() ?: 0)
            },
            label = { Text("Кількість") },
            modifier = Modifier.width(100.dp)
        )
    }
}
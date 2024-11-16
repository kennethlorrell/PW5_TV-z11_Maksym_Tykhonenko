package com.deepdark.lab5.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deepdark.lab5.utils.electricitySystemItemList
import com.deepdark.lab5.utils.roundTo
import com.deepdark.lab5.components.ElectricitySystemSelect
import com.deepdark.lab5.components.ItemSelectorWithDropdown
import com.deepdark.lab5.data.ElectricitySystemItem
import com.deepdark.lab5.data.ReliabilityComparisonCalculationResult
import com.deepdark.lab5.data.calculateReliability

@Composable
fun ReliabilityComparisonPage() {
    val selectedSingleCircuitItems = remember { mutableStateMapOf(
        Pair(ElectricitySystemItem("В-110 кВ (елегазовий)", 0.01, 40.0, 0.33, 15.0), 1),
        Pair(ElectricitySystemItem("ПЛ-110 кВ", 0.07, 10.0, 0.167, 35.0), 1),
        Pair(ElectricitySystemItem("Т-110 кВ", 0.015, 100.0, 1.0, 43.0), 1),
        Pair(ElectricitySystemItem("В-10 кВ (малооливний)", 0.02, 15.0, 0.33, 15.0), 1),
        Pair(ElectricitySystemItem("Збірні шини 10 кВ на 1 приєднання", 0.03, 2.0, 0.167, 5.0), 6)
    ) }

    var calculationResult by remember { mutableStateOf<ReliabilityComparisonCalculationResult?>(null) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Виберіть елементи однофазної системи:")

        ElectricitySystemSelect(allItems = electricitySystemItemList) { selectedItem ->
            selectedSingleCircuitItems[selectedItem] = (selectedSingleCircuitItems[selectedItem] ?: 0) + 1
        }

        selectedSingleCircuitItems.forEach { (item, count) ->
            ItemSelectorWithDropdown(
                item = item,
                count = count,
                onCountChange = { newCount ->
                    if (newCount == 0) {
                        selectedSingleCircuitItems.remove(item)
                    } else {
                        selectedSingleCircuitItems[item] = newCount
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                calculationResult = calculateReliability(selectedSingleCircuitItems)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Розрахувати надійність")
        }

        Spacer(modifier = Modifier.height(16.dp))

        calculationResult?.let {
            Text("Частота відмов одноколової системи: ${it.totalFailureRateSingleCircuit.roundTo(4)}")
            Text("Середня тривалість відновлення: ${it.averageRestorationTime.roundTo(4)} год")
            Text("Коефіцієнт аварійного простою одноколової системи: ${it.accidentalOutageCoefficient.roundTo(4)}")
            Text("Коефіцієнт планового простою одноколової системи: ${it.plannedOutageCoefficient.roundTo(4)}")
            Text("Частота відмов одночасно двох кіл двоколової системи: ${it.simultaneousFailureRateDoubleCircuit.roundTo(4)}")
            Text("Загальна частота відмов двоколової системи: ${it.totalFailureRateDoubleCircuit.roundTo(4)}")
            Text(it.comparisonResult)
        }
    }
}

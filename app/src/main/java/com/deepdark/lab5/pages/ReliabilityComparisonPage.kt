package com.deepdark.lab5.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deepdark.lab5.components.ElectricitySystemSelect
import com.deepdark.lab5.components.ItemSelectorWithDropdown
import com.deepdark.lab5.data.ElectricitySystemItem
import com.deepdark.lab5.data.ReliabilityComparisonCalculationResult
import com.deepdark.lab5.data.calculateReliability

@Composable
fun ReliabilityComparisonPage() {
    val electricitySystemItemList = listOf(
        ElectricitySystemItem("ПЛ-110 кВ", 0.007, 10.0, 0.167, 35.0),
        ElectricitySystemItem("ПЛ-35 кВ", 0.02, 8.0, 0.167, 35.0),
        ElectricitySystemItem("ПЛ-10 кВ", 0.02, 10.0, 0.167, 35.0),
        ElectricitySystemItem("КЛ-10 кВ (траншея)", 0.03, 44.0, 1.0, 9.0),
        ElectricitySystemItem("КЛ-10 кВ (кабельний канал)", 0.005, 17.5, 1.0, 9.0),
        ElectricitySystemItem("Т-110 кВ", 0.015, 100.0, 1.0, 43.0),
        ElectricitySystemItem("Т-35 кВ", 0.02, 80.0, 1.0, 28.0),
        ElectricitySystemItem("Т-10 кВ (кабельна мережа)", 0.005, 60.0, 0.5, 10.0),
        ElectricitySystemItem("Т-10 кВ (повітряна мережа)", 0.01, 30.0, 1.0, 30.0),
        ElectricitySystemItem("В-110 кВ (елегазовий)", 0.01, 40.0, 0.33, 15.0),
        ElectricitySystemItem("В-10 кВ (малооливний)", 0.02, 15.0, 0.33, 15.0),
        ElectricitySystemItem("В-10 кВ (вакуумний)", 0.02, 10.0, 0.33, 15.0),
        ElectricitySystemItem("Збірні шини 10 кВ на 1 приєднання", 0.03, 2.0, 0.167, 5.0),
        ElectricitySystemItem("АВ-0,38 кВ", 0.05, 4.0, 0.33, 10.0),
        ElectricitySystemItem("ЕД 6, 10 кВ", 0.1, 160.0, 0.5, 0.0),
        ElectricitySystemItem("ЕД 0,38 кВ", 0.1, 50.0, 0.5, 0.0)
    )

    val selectedSingleCircuitItems = remember { mutableStateMapOf(
        Pair(ElectricitySystemItem("В-110 кВ (елегазовий)", 0.01, 40.0, 0.33, 15.0), 1),
        Pair(ElectricitySystemItem("ПЛ-110 кВ", 0.007, 10.0, 0.167, 35.0), 1),
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
            Text("Частота відмов одноколової системи: ${it.totalFailureRateSingleCircuit}")
            Text("Середня тривалість відновлення: ${it.averageRestorationTime} год")
            Text("Коефіцієнт аварійного простою одноколової системи: ${it.accidentalOutageCoefficient}")
            Text("Коефіцієнт планового простою одноколової системи: ${it.plannedOutageCoefficient}")
            Text("Частота відмов одночасно двох кіл двоколової системи: ${it.simultaneousFailureRateDoubleCircuit}")
            Text("Загальна частота відмов двоколової системи: ${it.totalFailureRateDoubleCircuit}")
            Text(it.comparisonResult)
        }
    }
}

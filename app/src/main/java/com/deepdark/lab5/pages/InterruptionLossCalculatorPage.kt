package com.deepdark.lab5.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deepdark.lab5.utils.roundTo
import com.deepdark.lab5.data.calculateInterruptionLoss
import com.deepdark.lab5.data.InterruptionLossResult

@Composable
fun InterruptionLossCalculatorPage() {
    var failureRate by remember { mutableStateOf("0.01") }
    var averageRestorationTime by remember { mutableStateOf("0.045") }
    var averageUnavailabilityTime by remember { mutableStateOf("0.004") }
    var costPerKwhEmergency by remember { mutableStateOf("23.6") }
    var costPerKwhPlanned by remember { mutableStateOf("17.6") }
    var loadPower by remember { mutableStateOf("5120") }
    var annualOperationHours by remember { mutableStateOf("6451") }

    var result by remember { mutableStateOf<InterruptionLossResult?>(null) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text("Введіть параметри ГПП:")

        OutlinedTextField(
            value = failureRate,
            onValueChange = { failureRate = it },
            label = { Text("Частота відмов") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = averageRestorationTime,
            onValueChange = { averageRestorationTime = it },
            label = { Text("Середній час відновлення") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = averageUnavailabilityTime,
            onValueChange = { averageUnavailabilityTime = it },
            label = { Text("Середній час планового простою") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = costPerKwhEmergency,
            onValueChange = { costPerKwhEmergency = it },
            label = { Text("Питомі збитки (аварійні)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = costPerKwhPlanned,
            onValueChange = { costPerKwhPlanned = it },
            label = { Text("Питомі збитки (планові)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = loadPower,
            onValueChange = { loadPower = it },
            label = { Text("Навантаження") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = annualOperationHours,
            onValueChange = { annualOperationHours = it },
            label = { Text("Річні години роботи") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                result = calculateInterruptionLoss(
                    failureRate = failureRate.toDouble(),
                    averageRestorationTime = averageRestorationTime.toDouble(),
                    averageUnavailabilityTime = averageUnavailabilityTime.toDouble(),
                    costPerKwhEmergency = costPerKwhEmergency.toDouble(),
                    costPerKwhPlanned = costPerKwhPlanned.toDouble(),
                    loadPower = loadPower.toDouble(),
                    annualOperationHours = annualOperationHours.toDouble()
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Розрахувати збитки")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text("Математичне сподівання аварійного недопущення електроенергії становить: ${it.expectedUnavailabilityAccidental.roundTo(2)} кВт·год")
            Text("Математичне сподівання планового недопущення електроенергії становить: ${it.expectedUnavailabilityPlanned.roundTo(2)} кВт·год")
            Text("Математичне сподівання збитків від переривання електроенергії становить: ${it.expectedFinancialLoss.roundTo(2)} грн")
        }
    }
}

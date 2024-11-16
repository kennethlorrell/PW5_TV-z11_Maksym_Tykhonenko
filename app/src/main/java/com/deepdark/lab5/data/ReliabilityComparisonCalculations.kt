package com.deepdark.lab5.data

import com.deepdark.lab5.utils.HOURS_IN_YEAR

fun calculateReliability(selectedItems: Map<ElectricitySystemItem, Int>): ReliabilityComparisonCalculationResult {
    // Знаходимо частоту відмов одноколової системи
    val totalFailureRateSingleCircuit = selectedItems.entries.sumOf { (item, count) ->
        item.failureRate * count
    }

    // Порахуємо тривалість відновлення всієї одноколової системи
    val weightedRestorationTimeSum = selectedItems.entries.sumOf { (item, count) ->
        item.repairTime * item.failureRate * count
    }

    // Отримаємо середню тривалість відновлення одноколової системи
    val averageRestorationTime = if (totalFailureRateSingleCircuit != 0.0) {
        weightedRestorationTimeSum / totalFailureRateSingleCircuit
    } else 0.0

    // Коефіцієнт аварійного простою одноколової системи
    val accidentalOutageCoefficient = totalFailureRateSingleCircuit * averageRestorationTime / HOURS_IN_YEAR

    // Коефіцієнт планового простою одноколової системи
    val plannedOutageCoefficient = 1.2 * 43.0 / HOURS_IN_YEAR

    // Частота відмови одночасно двох кіл двоколової системи
    val simultaneousFailureRateDoubleCircuit = 2 * (totalFailureRateSingleCircuit * accidentalOutageCoefficient + plannedOutageCoefficient)

    // Загальна частота відмов двоколової системи з урахуванням вимикача
    val totalFailureRateDoubleCircuit = simultaneousFailureRateDoubleCircuit + 0.02

    // Порівняємо надійність обох систем
    val comparisonResult = if (totalFailureRateSingleCircuit < totalFailureRateDoubleCircuit) {
        "Надійність одноколової системи є вищою, ніж двоколової."
    } else if (totalFailureRateSingleCircuit > totalFailureRateDoubleCircuit) {
        "Надійність двоколової системи є вищою, ніж одноколової."
    } else {
        "Надійність обох систем рівна між собою."
    }

    return ReliabilityComparisonCalculationResult(
        totalFailureRateSingleCircuit = totalFailureRateSingleCircuit,
        averageRestorationTime = averageRestorationTime,
        accidentalOutageCoefficient = accidentalOutageCoefficient,
        plannedOutageCoefficient = plannedOutageCoefficient,
        simultaneousFailureRateDoubleCircuit = simultaneousFailureRateDoubleCircuit,
        totalFailureRateDoubleCircuit = totalFailureRateDoubleCircuit,
        comparisonResult = comparisonResult
    )
}
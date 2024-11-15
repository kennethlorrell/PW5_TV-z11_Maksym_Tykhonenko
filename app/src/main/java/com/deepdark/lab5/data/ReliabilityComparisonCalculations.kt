package com.deepdark.lab5.data

import com.deepdark.lab4.utils.HOURS_IN_YEAR

fun calculateReliability(selectedItems: Map<ElectricitySystemItem, Int>): ReliabilityComparisonCalculationResult {
    val totalFailureRateSingleCircuit = selectedItems.entries.sumOf { (item, count) ->
        item.failureRate * count
    }

    val weightedRestorationTimeSum = selectedItems.entries.sumOf { (item, count) ->
        item.repairTime * item.failureRate * count
    }

    val averageRestorationTime = if (totalFailureRateSingleCircuit != 0.0) {
        weightedRestorationTimeSum / totalFailureRateSingleCircuit
    } else 0.0

    val accidentalOutageCoefficient = (totalFailureRateSingleCircuit * averageRestorationTime) / HOURS_IN_YEAR

    val plannedOutageCoefficient = (1.2 * 43.0) / HOURS_IN_YEAR

    val simultaneousFailureRateDoubleCircuit = 2 * (totalFailureRateSingleCircuit * accidentalOutageCoefficient + plannedOutageCoefficient)

    val totalFailureRateDoubleCircuit = simultaneousFailureRateDoubleCircuit + 0.02

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
package com.deepdark.lab5.data

data class ReliabilityComparisonCalculationResult(
    val totalFailureRateSingleCircuit: Double,
    val averageRestorationTime: Double,
    val accidentalOutageCoefficient: Double,
    val plannedOutageCoefficient: Double,
    val simultaneousFailureRateDoubleCircuit: Double,
    val totalFailureRateDoubleCircuit: Double,
    val comparisonResult: String
)
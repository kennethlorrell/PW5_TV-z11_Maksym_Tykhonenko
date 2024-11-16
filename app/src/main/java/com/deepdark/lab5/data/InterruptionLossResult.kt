package com.deepdark.lab5.data

data class InterruptionLossResult(
    val expectedUnavailabilityAccidental: Double,
    val expectedUnavailabilityPlanned: Double,
    val expectedFinancialLoss: Double
)
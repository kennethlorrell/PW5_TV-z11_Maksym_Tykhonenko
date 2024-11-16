package com.deepdark.lab5.data

fun calculateInterruptionLoss(
    failureRate: Double,
    averageRestorationTime: Double,
    averageUnavailabilityTime: Double,
    costPerKwhEmergency: Double,
    costPerKwhPlanned: Double,
    loadPower: Double,
    annualOperationHours: Double
): InterruptionLossResult {
    // Рахуємо математичне сподівання аварійного недопущення електроенергії
    val expectedUnavailabilityAccidental = failureRate * loadPower * annualOperationHours * averageRestorationTime

    // Обчислимо математичне сподівання планового недопущення електроенергії
    val expectedUnavailabilityPlanned = averageUnavailabilityTime * loadPower * annualOperationHours

    // Розраховуємо математичне сподівання збитків від переривання електроенергії
    val expectedFinancialLoss = (costPerKwhEmergency * expectedUnavailabilityAccidental) + (costPerKwhPlanned * expectedUnavailabilityPlanned)

    return InterruptionLossResult(
        expectedUnavailabilityAccidental = expectedUnavailabilityAccidental,
        expectedUnavailabilityPlanned = expectedUnavailabilityPlanned,
        expectedFinancialLoss = expectedFinancialLoss
    )
}

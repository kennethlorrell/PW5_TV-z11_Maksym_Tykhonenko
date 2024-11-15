package com.deepdark.lab5.data

data class ElectricitySystemItem(
    val name: String,
    val failureRate: Double,
    val repairTime: Double,
    val outageRate: Double,
    val avgRepairTime: Double
)
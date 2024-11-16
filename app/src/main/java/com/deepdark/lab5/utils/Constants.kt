package com.deepdark.lab5.utils

import com.deepdark.lab5.data.ElectricitySystemItem

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

val HOURS_IN_YEAR = 8760
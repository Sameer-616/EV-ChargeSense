package com.example.evchargesense

data class ChargingStation(
    val name: String,
    val distanceKm: Double,
    val availablePorts: Int,
    val isAvailable: Boolean,
    val portType: String
)

val mockStations = listOf(
    ChargingStation(name = "GreenCharge Station", distanceKm = 2.4, availablePorts = 3, isAvailable = true, portType = "Type 2 (AC)"),
    ChargingStation(name = "PowerVolt Station", distanceKm = 4.1, availablePorts = 0, isAvailable = false, portType = "CCS Combo"),
    ChargingStation(name = "QuickCharge Hub", distanceKm = 5.6, availablePorts = 5, isAvailable = true, portType = "CHAdeMO")
)

fun recommendStation(stations: List<ChargingStation>): ChargingStation? =
    stations.filter { it.isAvailable }.minByOrNull { it.distanceKm }
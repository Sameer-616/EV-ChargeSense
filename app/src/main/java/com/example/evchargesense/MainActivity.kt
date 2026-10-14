package com.example.evchargesense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.evchargesense.ui.theme.EVChargeSenseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EVChargeSenseTheme {
                val navController = rememberNavController()
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = currentRoute == "dashboard",
                                onClick = { navController.navigate("dashboard") { launchSingleTop = true } },
                                icon = { Text(text = "🏠", fontSize = 20.sp) },
                                label = { Text(text = "Home") }
                            )
                            NavigationBarItem(
                                selected = currentRoute == "battery",
                                onClick = { navController.navigate("battery") { launchSingleTop = true } },
                                icon = { Text(text = "🔋", fontSize = 20.sp) },
                                label = { Text(text = "Battery") }
                            )
                            NavigationBarItem(
                                selected = currentRoute == "stations",
                                onClick = { navController.navigate("stations") { launchSingleTop = true } },
                                icon = { Text(text = "📍", fontSize = 20.sp) },
                                label = { Text(text = "Stations") }
                            )
                            NavigationBarItem(
                                selected = currentRoute == "notifications",
                                onClick = { navController.navigate("notifications") { launchSingleTop = true } },
                                icon = { Text(text = "🔔", fontSize = 20.sp) },
                                label = { Text(text = "Alerts") }
                            )
                            NavigationBarItem(
                                selected = currentRoute == "profile",
                                onClick = { navController.navigate("profile") { launchSingleTop = true } },
                                icon = { Text(text = "👤", fontSize = 20.sp) },
                                label = { Text(text = "Profile") }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "dashboard",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("dashboard") {
                            DashboardScreen(
                                onFindStationClick = { navController.navigate("stations") },
                                onBatteryDetailsClick = { navController.navigate("battery") }
                            )
                        }
                        composable("battery") {
                            BatteryScreen(
                                onFindStationClick = { navController.navigate("stations") }
                            )
                        }
                        composable("stations") {
                            StationsScreen(
                                onViewDetailsClick = { station ->
                                    navController.navigate("stationDetails/${station.name}")
                                },
                                onRecommendedClick = { navController.navigate("recommended") }
                            )
                        }
                        composable("stationDetails/{stationName}") { backStackEntry ->
                            val stationName = backStackEntry.arguments?.getString("stationName") ?: ""
                            val station = mockStations.find { it.name == stationName }
                            if (station != null) {
                                StationDetailsScreen(station = station)
                            }
                        }
                        composable("recommended") {
                            RecommendedStationScreen(
                                onViewStationClick = { station ->
                                    navController.navigate("stationDetails/${station.name}")
                                },
                                onNavigateClick = { /* TODO: Stage 6 — real GPS navigation */ }
                            )
                        }
                        composable("notifications") {
                            NotificationsScreen(
                                onViewStationClick = { navController.navigate("recommended") }
                            )
                        }
                        composable("profile") {
                            ProfileScreen()
                        }
                    }
                }
            }
        }
    }
}
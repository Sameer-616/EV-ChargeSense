package com.example.evchargesense

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evchargesense.ui.theme.EVChargeSenseTheme
import com.example.evchargesense.ui.theme.EVGreen

@Composable
fun RecommendedStationScreen(
    modifier: Modifier = Modifier,
    stations: List<ChargingStation> = mockStations,
    onViewStationClick: (ChargingStation) -> Unit = {},
    onNavigateClick: (ChargingStation) -> Unit = {}
) {
    val recommended = recommendStation(stations)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Recommended Charging Station", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        if (recommended == null) {
            Text(text = "No available stations nearby right now.", fontSize = 16.sp)
        } else {
            Text(text = "⭐", fontSize = 40.sp)

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = recommended.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Distance: ${recommended.distanceKm} km", fontSize = 14.sp)
                    Text(text = "Available Ports: ${recommended.availablePorts}", fontSize = 14.sp)
                    Text(text = "Availability: Available", fontSize = 14.sp, color = EVGreen, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { onViewStationClick(recommended) }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "View Station")
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(onClick = { onNavigateClick(recommended) }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Navigate")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendedStationScreenPreview() {
    EVChargeSenseTheme {
        RecommendedStationScreen()
    }
}
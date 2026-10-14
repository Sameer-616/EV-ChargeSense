package com.example.evchargesense

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evchargesense.ui.theme.EVChargeSenseTheme
import com.example.evchargesense.ui.theme.EVGreen
import com.example.evchargesense.ui.theme.EVRed

@Composable
fun StationsScreen(
    modifier: Modifier = Modifier,
    stations: List<ChargingStation> = mockStations,
    onViewDetailsClick: (ChargingStation) -> Unit = {},
    onRecommendedClick: () -> Unit = {}
) {
    Column(modifier = modifier.fillMaxSize().padding(24.dp)) {
        Text(text = "Nearby Charging Stations", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = onRecommendedClick, modifier = Modifier.fillMaxWidth()) {
            Text(text = "⭐ View Recommended Station")
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(stations) { station ->
                StationCard(
                    station = station,
                    onViewDetailsClick = { onViewDetailsClick(station) }
                )
            }
        }
    }
}

@Composable
fun StationCard(
    station: ChargingStation,
    onViewDetailsClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = station.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "${station.distanceKm} km", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (station.isAvailable) "${station.availablePorts} ports available" else "No ports available",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (station.isAvailable) "Available" else "Unavailable",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (station.isAvailable) EVGreen else EVRed
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onViewDetailsClick, modifier = Modifier.fillMaxWidth()) {
                Text(text = "View Details")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StationsScreenPreview() {
    EVChargeSenseTheme { StationsScreen() }
}
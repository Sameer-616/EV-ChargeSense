package com.example.evchargesense

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evchargesense.ui.theme.EVChargeSenseTheme
import com.example.evchargesense.ui.theme.EVGreen
import com.example.evchargesense.ui.theme.EVRed

@Composable
fun StationDetailsScreen(
    modifier: Modifier = Modifier,
    station: ChargingStation
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(text = "Station Details", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = station.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                DetailRow(label = "Distance", value = "${station.distanceKm} km")
                DetailRow(label = "Available Ports", value = "${station.availablePorts}")
                DetailRow(label = "Port Type", value = station.portType)
                DetailRow(
                    label = "Availability",
                    value = if (station.isAvailable) "Available" else "Unavailable",
                    valueColor = if (station.isAvailable) EVGreen else EVRed
                )
            }
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
    valueColor: Color = Color.Unspecified
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 14.sp)
        Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = valueColor)
    }
}

@Preview(showBackground = true)
@Composable
fun StationDetailsScreenPreview() {
    EVChargeSenseTheme {
        StationDetailsScreen(station = mockStations[0])
    }
}
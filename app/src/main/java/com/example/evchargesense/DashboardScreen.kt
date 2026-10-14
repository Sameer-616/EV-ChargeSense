package com.example.evchargesense

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evchargesense.ui.theme.EVChargeSenseTheme
import com.example.evchargesense.ui.theme.EVGreen

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    onFindStationClick: () -> Unit = {},
    onBatteryDetailsClick: () -> Unit = {}
) {
    val vehicleStatus = "NORMAL"
    val batteryPercent = 79
    val remainingRangeKm = 299

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "EV ChargeSense", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.ev_car),
            contentDescription = "EV vehicle",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Vehicle Status", fontSize = 14.sp)
                Text(text = vehicleStatus, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = EVGreen)

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "$batteryPercent%", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = EVGreen)
                Text(text = "Battery", fontSize = 14.sp)

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "$remainingRangeKm KM", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "Remaining Range", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onFindStationClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Find Charging Station")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onBatteryDetailsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Battery Details")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    EVChargeSenseTheme {
        DashboardScreen()
    }
}
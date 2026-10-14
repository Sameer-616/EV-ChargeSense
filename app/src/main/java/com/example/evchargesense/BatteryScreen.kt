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
import com.example.evchargesense.ui.theme.EVRed

private const val LOW_BATTERY_THRESHOLD = 20

@Composable
fun BatteryScreen(
    modifier: Modifier = Modifier,
    batteryPercent: Int = 79,
    remainingRangeKm: Int = 299,
    isCharging: Boolean = false,
    onFindStationClick: () -> Unit = {}
) {
    val isLowBattery = batteryPercent < LOW_BATTERY_THRESHOLD

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Battery Monitoring", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$batteryPercent%",
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isLowBattery) EVRed else EVGreen
                )
                Text(text = "Battery Level", fontSize = 14.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "$remainingRangeKm KM", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text(text = "Estimated Range", fontSize = 14.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (isCharging) "Charging" else "Not Charging",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isCharging) EVGreen else MaterialTheme.colorScheme.onSurface
                )
                Text(text = "Charging Status", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (isLowBattery) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = EVRed.copy(alpha = 0.15f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "⚠ Low Battery", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EVRed)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "Your battery level is low. Nearby charging stations are recommended.", fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onFindStationClick, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Find Charging Station")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BatteryScreenNormalPreview() {
    EVChargeSenseTheme { BatteryScreen(batteryPercent = 79, remainingRangeKm = 299) }
}

@Preview(showBackground = true)
@Composable
fun BatteryScreenLowPreview() {
    EVChargeSenseTheme { BatteryScreen(batteryPercent = 15, remainingRangeKm = 40) }
}
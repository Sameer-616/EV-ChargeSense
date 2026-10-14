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
import com.example.evchargesense.ui.theme.EVRed

data class AppNotification(
    val title: String,
    val message: String,
    val isAlert: Boolean = false
)

val mockNotifications = listOf(
    AppNotification(
        title = "⚠ Low Battery",
        message = "Battery is at 18%. A nearby charging station is available 2.4 km away.",
        isAlert = true
    ),
    AppNotification(
        title = "Charging Started",
        message = "Your vehicle started charging at GreenCharge Station."
    ),
    AppNotification(
        title = "Station Update",
        message = "QuickCharge Hub now has 5 ports available."
    )
)

@Composable
fun NotificationsScreen(
    modifier: Modifier = Modifier,
    notifications: List<AppNotification> = mockNotifications,
    onViewStationClick: () -> Unit = {}
) {
    Column(modifier = modifier.fillMaxSize().padding(24.dp)) {
        Text(text = "Notifications", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(notifications) { notification ->
                NotificationCard(
                    notification = notification,
                    onViewStationClick = onViewStationClick
                )
            }
        }
    }
}

@Composable
fun NotificationCard(
    notification: AppNotification,
    onViewStationClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = notification.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (notification.isAlert) EVRed else MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = notification.message, fontSize = 14.sp)

            if (notification.isAlert) {
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onViewStationClick, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "View Station")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsScreenPreview() {
    EVChargeSenseTheme {
        NotificationsScreen()
    }
}
package com.example.evchargesense.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import com.evchargesense.app.ui.theme.EVBackground
import com.evchargesense.app.ui.theme.EVGreen
import com.evchargesense.app.ui.theme.EVRed
import com.evchargesense.app.ui.theme.EVSurface
import com.evchargesense.app.ui.theme.EVTextPrimary

private val EVChargeSenseColorScheme = darkColorScheme(
    background = EVBackground,
    surface = EVSurface,
    primary = EVGreen,
    error = EVRed,
    onBackground = EVTextPrimary,
    onSurface = EVTextPrimary,
    onPrimary = EVTextPrimary
)

@Composable
fun EVChargeSenseTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EVChargeSenseColorScheme,
        typography = Typography,
        content = content
    )
}
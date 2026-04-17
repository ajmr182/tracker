package com.ajmr.tracker.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val DarkColors = darkColorScheme(
    primary = Color(0xFF6366F1),
    onPrimary = Color(0xFFFFFFFF),

    secondary = Color(0xFF22C55E),
    onSecondary = Color(0xFF052E1F),

    error = Color(0xFFF87171),
    onError = Color(0xFF3F1D1D),

    background = Color(0xFF0F172A),
    onBackground = Color(0xFFE5E7EB),

    surface = Color(0xFF1E293B),
    onSurface = Color(0xFFE5E7EB),

    outline = Color(0xFF334155)
)

val LightColors = lightColorScheme(
    primary = Color(0xFF4F46E5),
    onPrimary = Color(0xFFFFFFFF),

    secondary = Color(0xFF22C55E),
    onSecondary = Color(0xFFFFFFFF),

    error = Color(0xFFEF4444),
    onError = Color(0xFFFFFFFF),

    background = Color(0xFFF8FAFC),
    onBackground = Color(0xFF111827),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF111827),

    outline = Color(0xFFE5E7EB)
)

@Composable
fun TrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
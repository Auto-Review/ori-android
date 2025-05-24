package com.dd2d.core.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme()

val MainColor = Color(0xFFFF8B00)

private val LightColorScheme = lightColorScheme(
    primary = MainColor,
    onPrimary = Color(0xFFFFFFFF),

    background = Color(0xFFFFFFFF),
    onBackground = Color.Black,

    error = Color(0xFFA93A3A),

    surface = Color(0xFFF5F5F5),
    onSurface = Color(0xFF1E1E1E),
    onSurfaceVariant = Color(0xFF979797),

    surfaceBright = Color(0xFFBABABA),
    surfaceContainer = Color(0xFFF4F6FF),

    outlineVariant = Color(0xFFF5F5F5)
)

@Composable
fun AppTheme(
    darkMode: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkMode) DarkColorScheme else LightColorScheme,
        shapes = Shapes(
            extraSmall = RoundedCornerShape(4.dp),
            small = RoundedCornerShape(8.dp),
            medium = RoundedCornerShape(15.dp),
            large = RoundedCornerShape(30.dp),
            extraLarge = RoundedCornerShape(45.dp),
        ),
        content = content
    )
}
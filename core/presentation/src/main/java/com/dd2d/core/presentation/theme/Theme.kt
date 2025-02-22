package com.dd2d.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFD369),
    onPrimary = Color(0xfffffcfc),
    inversePrimary = Color(0xFFC4A74D),

    secondary = PurpleGrey80,
    tertiary = Pink80,

    background = Color.Black,
    onBackground = Color.White,

    error = Color(0xFFE75858),

    surface = Color.Black,
    onSurface = Color.White,


    surfaceContainer = Color(0xFF222831),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFF8B00),
    onPrimary = Color(0xFFFFFFFF),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1E1E1E),

    error = Color(0xFFA93A3A),

    surface = Color(0xFFF5F5F5),
    onSurface = Color.Black,


    surfaceContainer = Color(0xFFF4F6FF),
)

@Composable
fun AppTheme(
//    darkMode: Boolean = isSystemInDarkTheme(),
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
        typography = Typography,
        content = content
    )
}
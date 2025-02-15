package com.dd2d.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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
    primary = Color(0xFFFFD369),
    onPrimary = Color(0xfffffcfc),
    inversePrimary = Color(0xFFC4A74D),

    secondary = PurpleGrey40,
    tertiary = Pink40,

    background = Color.White,
    onBackground = Color.Black,

    error = Color(0xFFA93A3A),

    surface = Color.White,
    onSurface = Color.Black,

    surfaceContainer = Color(0xFFF4F6FF),
)

@Composable
fun AppTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkMode) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}
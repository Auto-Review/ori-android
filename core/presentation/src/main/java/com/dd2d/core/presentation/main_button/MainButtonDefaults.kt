package com.dd2d.core.presentation.main_button

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class MainButtonColors(
    val containerColor: Color,
    val contentColor: Color,

    val loadingContainerColor: Color,
    val loadingContentColor: Color,

    val errorContainerColor: Color,
    val errorContentColor: Color,

    val disabledContainerColor: Color,
    val disabledContentColor: Color,
)

object MainButtonDefaults {
    @Composable
    fun mainButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.primary,
        contentColor: Color = MaterialTheme.colorScheme.onPrimary,
        loadingContainerColor: Color = containerColor,
        loadingContentColor: Color = contentColor,
        errorContainerColor: Color = MaterialTheme.colorScheme.error,
        errorContentColor: Color = MaterialTheme.colorScheme.onError,
        disabledContainerColor: Color = MaterialTheme.colorScheme.inversePrimary,
        disabledContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    ): MainButtonColors {
        return MainButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            loadingContainerColor = loadingContainerColor,
            loadingContentColor = loadingContentColor,
            errorContainerColor = errorContainerColor,
            errorContentColor = errorContentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        )
    }
}
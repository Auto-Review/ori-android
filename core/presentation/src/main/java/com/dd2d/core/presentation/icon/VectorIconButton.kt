package com.dd2d.core.presentation.icon

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VectorIconButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    description: String? = null,
    iconSize: Dp = 24.dp,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        interactionSource = interactionSource,
        enabled = enabled,
        colors = colors,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Composable
fun VectorIconButton(
    @DrawableRes res: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    description: String? = null,
    iconSize: Dp = 24.dp,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        interactionSource = interactionSource,
        enabled = enabled,
        colors = colors,
        modifier = modifier
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = res),
            contentDescription = description,
            modifier = Modifier.size(iconSize)
        )
    }
}
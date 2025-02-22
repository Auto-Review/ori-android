package com.dd2d.core.presentation.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.dd2d.core.presentation.R

@Composable
fun PrevButton(
    modifier: Modifier = Modifier,
    prevIcon: ImageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
    enabled: Boolean = true,
    description: String = stringResource(R.string.prev),
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Icon(
            imageVector = prevIcon,
            contentDescription = description,
            tint = tint
        )
    }
}

@Composable
fun NextButton(
    modifier: Modifier = Modifier,
    nextIcon: ImageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
    enabled: Boolean = true,
    description: String = stringResource(R.string.next),
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Icon(
            imageVector = nextIcon,
            contentDescription = description,
            tint = tint
        )
    }
}
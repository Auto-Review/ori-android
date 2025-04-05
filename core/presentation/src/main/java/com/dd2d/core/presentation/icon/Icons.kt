package com.dd2d.core.presentation.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.dd2d.core.presentation.R

@Composable
fun BackIcon(
    modifier: Modifier = Modifier,
    description: String? = stringResource(R.string.back),
    tint: Color = LocalContentColor.current
) {
    VectorIcon(
        icon = Icons.AutoMirrored.Default.ArrowBack,
        description = description,
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun CloseIcon(
    modifier: Modifier = Modifier,
    description: String? = stringResource(R.string.close),
    tint: Color = LocalContentColor.current
) {
    VectorIcon(
        icon = Icons.Default.Close,
        description = description,
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun AddIcon(
    modifier: Modifier = Modifier,
    description: String? = stringResource(R.string.add),
    tint: Color = LocalContentColor.current
) {
    VectorIcon(
        icon = Icons.Default.Add,
        description = description,
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun DetailIcon(
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    VectorIcon(
        icon = Icons.AutoMirrored.Default.KeyboardArrowRight,
        description = null,
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun BookmarkIcon(
    onBookMark: Boolean,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    VectorIcon(
        res = if(onBookMark) R.drawable.bookmark_fill else R.drawable.bookmark,
        description = null,
        tint = tint,
        modifier = modifier
    )
}

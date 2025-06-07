package com.dd2d.core.presentation.surface

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ColumnSurface(
  modifier: Modifier = Modifier,
  shape: Shape = RectangleShape,
  color: Color = MaterialTheme.colorScheme.surface,
  contentColor: Color = contentColorFor(color),
  elevation: Dp = 0.dp,
  border: BorderStroke? = null,
  verticalArrangement: Arrangement.Vertical = Arrangement.Top,
  horizontalAlignment: Alignment.Horizontal = Alignment.Start,
  contentPadding: PaddingValues = PaddingValues(),
  content: @Composable ColumnScope.() -> Unit
) {
  Surface(
    shape = shape,
    color = color,
    contentColor = contentColor,
    shadowElevation = elevation,
    border = border,
    modifier = modifier,
  ) {
    Column(
      verticalArrangement = verticalArrangement,
      horizontalAlignment = horizontalAlignment,
      content = content,
      modifier = Modifier
        .fillMaxWidth()
        .padding(contentPadding)
    )
  }
}

@Composable
fun ColumnSurface(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  interactionSource: MutableInteractionSource? = null,
  enabled: Boolean = true,
  shape: Shape = RectangleShape,
  color: Color = MaterialTheme.colorScheme.surface,
  contentColor: Color = contentColorFor(color),
  elevation: Dp = 0.dp,
  border: BorderStroke? = null,
  verticalArrangement: Arrangement.Vertical = Arrangement.Top,
  horizontalAlignment: Alignment.Horizontal = Alignment.Start,
  contentPadding: PaddingValues = PaddingValues(),
  content: @Composable ColumnScope.() -> Unit
) {
  Surface(
    onClick = onClick,
    enabled = enabled,
    interactionSource = interactionSource,
    shape = shape,
    color = color,
    contentColor = contentColor,
    shadowElevation = elevation,
    border = border,
    modifier = modifier,
  ) {
    Column(
      verticalArrangement = verticalArrangement,
      horizontalAlignment = horizontalAlignment,
      content = content,
      modifier = Modifier
        .fillMaxWidth()
        .padding(contentPadding)
    )
  }
}
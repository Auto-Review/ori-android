package com.dd2d.core.presentation.surface

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
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
fun RowSurface(
  modifier: Modifier = Modifier,
  shape: Shape = RectangleShape,
  color: Color = MaterialTheme.colorScheme.surface,
  contentColor: Color = contentColorFor(color),
  elevation: Dp = 0.dp,
  border: BorderStroke? = null,
  verticalAlignment: Alignment.Vertical = Alignment.Top,
  horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
  contentPadding: PaddingValues = PaddingValues(),
  content: @Composable RowScope.() -> Unit
) {
  Surface(
    shape = shape,
    color = color,
    contentColor = contentColor,
    shadowElevation = elevation,
    border = border,
    modifier = modifier,
  ) {
    Row(
      horizontalArrangement = horizontalArrangement,
      verticalAlignment = verticalAlignment,
      content = content,
      modifier = Modifier
        .fillMaxSize()
        .padding(contentPadding)
    )
  }
}
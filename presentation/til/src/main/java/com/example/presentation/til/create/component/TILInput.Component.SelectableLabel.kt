package com.example.presentation.til.create.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main700Text

@Composable
internal fun SelectableLabel(
  label: String,
  isSelected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  selectedColor: Color = MaterialTheme.colorScheme.onSurface,
  unselectedColor: Color = MaterialTheme.colorScheme.outlineVariant,
  shape: Shape = MaterialTheme.shapes.small
) {
  Main700Text(
    text = label,
    color = selectedColor,
    fontSize = 12.sp,
    lineHeight = 24.sp,
    textAlign = TextAlign.Center,
    modifier = modifier
      .clip(shape)
      .border(
        width = if (isSelected) 2.dp else 1.dp,
        color = if (isSelected) selectedColor else unselectedColor,
        shape = shape
      )
      .clickable(onClick = onClick)
      .padding(horizontal = 16.dp, vertical = 8.dp)
  )
}
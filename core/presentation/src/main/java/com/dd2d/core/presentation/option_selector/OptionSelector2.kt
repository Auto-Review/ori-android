package com.dd2d.core.presentation.option_selector

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.dd2d.core.presentation.main_text.Main700Text

@Composable
fun OptionSelector2(
  open: Boolean,
  close: () -> Unit,
  options: List<String>,
  onOptionSelected: (index: Int) -> Unit,
  modifier: Modifier = Modifier,
  shape: Shape = RectangleShape,
  containerColor: Color = MaterialTheme.colorScheme.background,
  elevation: Dp = 5.dp,
  border: BorderStroke? = null,
  popupProperties: PopupProperties = PopupProperties(),
  scrollState: ScrollState = rememberScrollState(),
) {
  DropdownMenu(
    expanded = open,
    onDismissRequest = close,
    modifier = modifier,
    offset = DpOffset.Unspecified,
    scrollState = scrollState,
    properties = popupProperties,
    shape = shape,
    containerColor = containerColor,
    tonalElevation = elevation,
    shadowElevation = elevation,
    border = border,
  ) {
    options.forEachIndexed { index, option ->
      DropdownMenuItem(
        text = {
          Main700Text(
            text = option,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurface
          )
        },
        onClick = { onOptionSelected(index) }
      )
    }
  }
}
package com.dd2d.core.presentation._ori

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main700Text

@Composable
inline fun InputItem(
  label: String,
  modifier: Modifier = Modifier,
  content: @Composable ColumnScope.() -> Unit
) {
  Column(modifier = modifier) {
    Main700Text(
      text = label,
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 12.sp,
      lineHeight = 24.sp,
    )
    content()
  }
}
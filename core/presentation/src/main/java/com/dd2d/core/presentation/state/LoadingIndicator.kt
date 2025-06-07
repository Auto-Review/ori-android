package com.dd2d.core.presentation.state

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.theme.AppTheme

@Composable
fun LoadingIndicator(
  modifier: Modifier = Modifier,
  width: Dp = 4.dp
) {
  CircularProgressIndicator(
    color = MaterialTheme.colorScheme.primary,
    strokeWidth = width,
    strokeCap = StrokeCap.Round,
    trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5F),
    modifier = modifier
  )
}

@Preview
@Composable
private fun LoadingIndicatorPrev() {
  AppTheme {
    LoadingIndicator(
    )
  }
}
package com.dd2d.core.presentation_oauth.google.component

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
internal fun BoxScope.DefaultLoadingIndicator() {
  CircularProgressIndicator(
    color = MaterialTheme.colorScheme.primary,
    trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5F),
    strokeCap = StrokeCap.Round,
    strokeWidth = 2.dp,
    modifier = Modifier
      .align(Alignment.CenterEnd)
      .size(20.dp)
  )
}

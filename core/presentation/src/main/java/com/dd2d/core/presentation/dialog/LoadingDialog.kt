package com.dd2d.core.presentation.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.theme.AppTheme

@Composable
fun LoadingDialog() {
  Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
  ) {
    CircularProgressIndicator(
      color = MaterialTheme.colorScheme.primary,
      strokeWidth = 4.dp,
      trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5F),
      strokeCap = StrokeCap.Round,
      modifier = Modifier
        .size(40.dp)
    )
  }
}

@Preview(showBackground = true)
@Preview(locale = "ko", showBackground = true)
@Composable
private fun LoadingDialogPrev() {
  AppTheme {

    Column(
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.Start,
      modifier = Modifier
        .fillMaxSize()
    ) {
      LoadingDialog(

      )
    }
  }
}
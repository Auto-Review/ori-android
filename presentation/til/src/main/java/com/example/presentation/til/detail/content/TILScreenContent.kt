package com.example.presentation.til.detail.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.til.model.TIL
import com.example.presentation.til.detail.component.TILContentComponent

@Composable
internal fun TILScreenContent(
  til: TIL,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 24.dp, vertical = 8.dp)
  ) {
    TILContentComponent(til = til)
  }
}

@Preview(showBackground = true)
@Composable
private fun TILScreenContentPrev() {
  AppTheme {
    TILScreenContent(
      til = TIL.dummy(),
      modifier = Modifier
        .background(Color.White)
    )
  }
}
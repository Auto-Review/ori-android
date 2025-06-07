package com.example.presentation.til.create.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.app_bar.CenterTitleTopBar
import com.dd2d.core.presentation.main_text.Main700Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TILScreenTopBar(
  onBack: () -> Unit,
  onCreate: () -> Unit,
  isCreating: Boolean,
  modifier: Modifier = Modifier
) {
  val keyboard = LocalSoftwareKeyboardController.current
  CenterTitleTopBar(
    title = "",
    onBack = onBack,
    actions = {
      if (isCreating) {
        CircularProgressIndicator(
          color = MaterialTheme.colorScheme.primary,
          strokeWidth = 2.dp,
          trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5F),
          modifier = Modifier.size(24.dp)
        )
      } else {
        Main700Text(
          text = "완료",
          color = MaterialTheme.colorScheme.onSurface,
          fontSize = 12.sp,
          lineHeight = 24.sp,
          modifier = Modifier
            .clickable {
              keyboard?.hide()
              onCreate()
            }
            .padding(5.dp)
        )
      }
    },
    modifier = modifier
  )
}
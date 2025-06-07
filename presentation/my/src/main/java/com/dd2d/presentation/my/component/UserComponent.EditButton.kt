package com.dd2d.presentation.my.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.extensions.delayedClickable
import com.dd2d.core.presentation.icon.VectorIconButton
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.state.LoadingIndicator

@Composable
internal fun EditButton(
  editMode: Boolean,
  onEditModeChange: (Boolean) -> Unit,
  canSave: Boolean,
  onSave: () -> Unit,
  isSaving: Boolean,
  modifier: Modifier = Modifier
) {
  AnimatedContent(
    targetState = editMode,
    transitionSpec = { fadeIn() togetherWith fadeOut() },
    modifier = modifier
  ) { edit ->
    if (edit) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
      ) {
        Main400Text(
          text = "취소",
          fontSize = 12.sp,
          lineHeight = 24.sp,
          modifier = Modifier
            .delayedClickable(onClick = { onEditModeChange(false) })
            .padding(horizontal = 4.dp, vertical = 2.dp)
        )
        AnimatedVisibility(visible = canSave) {
          Main400Text(
            text = "저장",
            fontSize = 12.sp,
            lineHeight = 24.sp,
            modifier = Modifier
              .padding(start = 4.dp)
              .delayedClickable(onClick = onSave)
              .padding(horizontal = 4.dp, vertical = 2.dp)
          )
        }
        AnimatedVisibility(visible = isSaving) {
          LoadingIndicator(width = 2.dp, modifier = Modifier.size(12.dp))
        }
      }
    } else {
      VectorIconButton(
        icon = Icons.Default.Edit,
        onClick = { onEditModeChange(true) },
        modifier = Modifier.scale(0.8F)
      )
    }
  }
}
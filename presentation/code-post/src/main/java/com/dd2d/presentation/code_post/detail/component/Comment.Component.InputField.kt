package com.dd2d.presentation.code_post.detail.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.main_text.Main600Text
import com.dd2d.core.presentation.main_text.Main700Text
import com.dd2d.core.presentation.state.LoadingIndicator
import com.dd2d.core.presentation.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun CommentInputFieldComponent(
  nickname: String?,
  content: String,
  onContentChange: (String) -> Unit,
  canCommit: Boolean,
  onCommit: () -> Unit,
  isCommiting: Boolean,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(top = 10.dp)
      .border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.outlineVariant,
        shape = MaterialTheme.shapes.small
      )
      .padding(12.dp)
  ) {
    nickname?.let {
      Main600Text(
        text = nickname,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 12.sp,
        lineHeight = 16.8.sp,
      )
    }
    BasicTextField(
      value = content,
      onValueChange = onContentChange,
      cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
      textStyle = TextStyle(
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 12.sp,
        lineHeight = 16.8.sp,
        fontWeight = FontWeight.W400,
      ),
      maxLines = 5,
      decorationBox = { innerTextField ->
        if (content.isBlank()) {
          Main400Text(
            text = "악플, 잘못된 정보는 경고없이 삭제될 수 있습니다.",
            color = MaterialTheme.colorScheme.surfaceBright,
            fontSize = 12.sp,
            lineHeight = 16.8.sp,
          )
        }
        innerTextField()
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
    )
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.End,
      modifier = Modifier.fillMaxWidth()
    ) {
      Main700Text(
        text = "COMMIT",
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        modifier = Modifier
          .graphicsLayer {
            alpha = if (canCommit) 1f else 0.5f
          }
          .clickable(enabled = canCommit && !isCommiting, onClick = onCommit)
          .padding(horizontal = 4.dp)
      )
      AnimatedVisibility(visible = isCommiting) {
        LoadingIndicator(width = 2.dp, modifier = Modifier.size(12.dp))
      }
    }
  }
}

@Preview
@Composable
private fun CommentInputFieldComponentPrev() {
  var isCommiting by remember { mutableStateOf(false) }

  val scope = rememberCoroutineScope()

  AppTheme {
    CommentInputFieldComponent(
      nickname = "nickname",
      content = "content",
      onContentChange = {},
      canCommit = true,
      onCommit = {
        scope.launch {
          isCommiting = true
          delay(1000)
          isCommiting = false
        }
      },
      isCommiting = isCommiting,
      modifier = Modifier
    )
  }
}
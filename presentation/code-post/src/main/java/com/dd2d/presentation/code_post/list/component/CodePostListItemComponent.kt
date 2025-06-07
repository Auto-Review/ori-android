package com.dd2d.presentation.code_post.list.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.extensions.delayedClickable
import com.dd2d.core.presentation.main_text.Main500Text
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.code_post.model.post.CodePostListItem

@Composable
internal fun CodePostListItemComponent(
  codePost: CodePostListItem,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .delayedClickable(onClick = onClick)
      .padding(horizontal = 24.dp, vertical = 16.dp)
  ) {
    Main500Text(
      text = codePost.title,
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 12.sp,
      lineHeight = 16.sp,
      modifier = Modifier.fillMaxWidth()
    )
    Main500Text(
      text = "${codePost.author.nickname}  ${codePost.createdAt} RE : ${codePost.commentCount}",
      color = MaterialTheme.colorScheme.surfaceBright,
      fontSize = 10.sp,
      lineHeight = 16.sp,
      modifier = Modifier.fillMaxWidth()
    )
  }
}

@Preview(showBackground = true)
@Preview(locale = "ko", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CodePostListItemComponentPrev() {
  AppTheme {
    Column(
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.Start,
      modifier = Modifier
        .fillMaxSize()
    ) {
      CodePostListItemComponent(
        codePost = CodePostListItem.dummy(),
        onClick = {},
      )
    }
  }
}
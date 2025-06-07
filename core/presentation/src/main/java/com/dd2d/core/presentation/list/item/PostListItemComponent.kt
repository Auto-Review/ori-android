package com.dd2d.core.presentation.list.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.core.model.DateString
import com.dd2d.core.presentation.extensions.delayedClickable
import com.dd2d.core.presentation.main_text.Main500Text

@Composable
fun PostListItemComponent(
  title: String,
  authorName: String,
  createdAt: DateString,
  commentCount: Int?,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .delayedClickable(onClick = onClick)
      .padding(horizontal = 24.dp, vertical = 16.dp)
  ) {
    Main500Text(
      text = title,
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 12.sp,
      lineHeight = 16.sp,
      modifier = Modifier.fillMaxWidth()
    )
    val commentCountText = commentCount?.let { " RE : $it" } ?: ""
    Main500Text(
      text = "$authorName  $createdAt$commentCountText",
      color = MaterialTheme.colorScheme.surfaceBright,
      fontSize = 10.sp,
      lineHeight = 16.sp,
      modifier = Modifier.fillMaxWidth()
    )
  }
}
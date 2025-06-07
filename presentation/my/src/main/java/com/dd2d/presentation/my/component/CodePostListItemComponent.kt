package com.dd2d.presentation.my.component

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.extensions.delayedClickable
import com.dd2d.core.presentation.main_text.Main500Text
import com.dd2d.domain.code_post.model.post.CodePostListItem

@Composable
internal fun CodePostListItemComponent(
  item: CodePostListItem,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
      .delayedClickable(onClick = onClick)
      .padding(horizontal = 24.dp, vertical = 7.dp)
  ) {
    Main500Text(
      text = item.title,
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 12.sp,
      lineHeight = 24.sp,
      modifier = Modifier
        .weight(1F)
        .basicMarquee()
    )
    Row(
      horizontalArrangement = Arrangement.spacedBy(15.dp),
      verticalAlignment = Alignment.Top,
      modifier = Modifier
    ) {
      Main500Text(
        text = "RE: ${item.commentCount}",
        color = MaterialTheme.colorScheme.surfaceBright,
        fontSize = 12.sp,
        lineHeight = 24.sp,
      )
      Main500Text(
        text = item.createdAt,
        color = MaterialTheme.colorScheme.surfaceBright,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        textAlign = TextAlign.End,
      )
    }
  }
}
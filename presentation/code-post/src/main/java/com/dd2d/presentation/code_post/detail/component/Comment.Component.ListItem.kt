package com.dd2d.presentation.code_post.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.icon.VectorIconButton
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.main_text.Main600Text
import com.dd2d.core.presentation.option_selector.OptionSelector
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.code_post.model.comment.CodePostCommentListItem


@Composable
internal fun CommentListItemComponent(
  isMine: Boolean,
  item: CodePostCommentListItem,
  onReport: () -> Unit,
  onEdit: () -> Unit,
  onDelete: () -> Unit,
  modifier: Modifier = Modifier
) {
  val options = remember {
    if (isMine) {
      listOf(
        "수정" to onEdit,
        "삭제" to onDelete
      )
    } else {
      listOf(
        "신고" to onReport
      )
    }
  }
  var openMenu by remember { mutableStateOf(false) }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 15.dp)
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
    ) {
      Main600Text(
        text = item.author.nickname,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 12.sp,
        lineHeight = 16.8.sp,
      )
      OptionSelector(
        open = openMenu,
        close = { openMenu = false },
        options = options.map { option -> option.first },
        onOptionSelected = { index ->
          openMenu = false
          options[index].second.invoke()
        },
      ) {
        VectorIconButton(
          icon = Icons.Default.MoreVert,
          modifier = Modifier.size(16.dp)
        ) {
          openMenu = !openMenu
        }
      }
    }
    Main400Text(
      text = item.content,
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 12.sp,
      lineHeight = 16.8.sp,
      modifier = Modifier.padding(vertical = 4.dp),
    )
    Main400Text(
      text = item.createdAt,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontSize = 12.sp,
      lineHeight = 16.8.sp,
    )
  }
}

@Preview(showBackground = true)
@Composable
private fun CommentListItemComponentPrev() {
  AppTheme {
    CommentListItemComponent(
      isMine = true,
      item = CodePostCommentListItem.dummy(),
      onReport = {},
      onEdit = {},
      onDelete = {},
      modifier = Modifier
    )
  }
}
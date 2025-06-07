package com.dd2d.core.presentation.app_bar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.extensions.delayedClickable
import com.dd2d.core.presentation.icon.BackIcon
import com.dd2d.core.presentation.icon.BookmarkIcon
import com.dd2d.core.presentation.icon.VectorIconButton
import com.dd2d.core.presentation.main_text.Main700Text
import com.dd2d.core.presentation.option_selector.OptionSelector2
import com.dd2d.core.presentation.theme.AppTheme
import kotlin.time.Duration.Companion.seconds

private enum class AuthorAction(val label: String) {
  Delete(label = "삭제"),
  Update(label = "수정"),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTapBar(
  title: String,
  onBack: () -> Unit,
  isScrapped: Boolean,
  toggleScrap: () -> Unit,
  isAuthor: Boolean,
  onUpdateClick: () -> Unit,
  onDeleteClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  var openMenu by remember { mutableStateOf(false) }

  TopAppBar(
    title = {
      Main700Text(
        text = title,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 20.sp,
        lineHeight = 24.sp,
      )
    },
    navigationIcon = {
      IconButton(onClick = onBack) {
        BackIcon()
      }
    },
    actions = {
      BookmarkIcon(
        onBookMark = isScrapped,
        modifier = Modifier
          .clip(CircleShape)
          .size(40.dp)
          .delayedClickable(
            delay = 0.5.seconds,
            onClick = toggleScrap,
            indication = ripple(bounded = false, radius = 60.dp)
          )
          .padding(10.dp)
      )
      if (isAuthor) {
        VectorIconButton(icon = Icons.Default.MoreVert, onClick = { openMenu = true })
        OptionSelector2(
          open = openMenu,
          close = { openMenu = false },
          options = AuthorAction.entries.map(AuthorAction::label),
          onOptionSelected = { index ->
            when (AuthorAction.entries[index]) {
              AuthorAction.Delete -> onDeleteClick()
              AuthorAction.Update -> onUpdateClick()
            }
          },
        )
      }
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.background,
      navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
      actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    ),
    modifier = modifier.fillMaxWidth(),
  )
}

@Preview
@Composable
private fun PostTapBarPrev() {
  AppTheme {
    PostTapBar(
      title = "게시물 제목",
      onBack = {},
      isScrapped = false,
      toggleScrap = {},
      isAuthor = true,
      onUpdateClick = {},
      onDeleteClick = {},
      modifier = Modifier
    )
  }
}
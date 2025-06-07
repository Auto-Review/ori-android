package com.dd2d.presentation.schedule.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.core.model.DateString
import com.dd2d.core.presentation.main_text.Main700Text
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.core.presentation.theme.LocalHansType
import com.dd2d.domain.notification.model.Notification

private val SCHEDULE_ITEM_HEIGHT = 40.dp

@Composable
internal fun ScheduleComponent(
  schedules: List<Notification>,
  onClick: (codePostId: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    Text(
      text = "// CHECK LIST",
      style = LocalHansType.current.label,
    )
    LazyColumn(
      modifier = Modifier
        .padding(top = 10.dp)
        .fillMaxWidth()
        .heightIn(max = SCHEDULE_ITEM_HEIGHT * 4)
        .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
        .padding(vertical = 10.dp)
    ) {
      if (schedules.isEmpty()) {
        item(key = "empty schedule") {
          Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
              .animateItem()
              .height(SCHEDULE_ITEM_HEIGHT)
              .padding(horizontal = 17.dp)
          ) {
            Main700Text(
              text = "이날은 리뷰할 게시물이 없어요.",
              fontSize = 12.sp,
              lineHeight = 24.sp,
              color = MaterialTheme.colorScheme.onSurface,
            )
          }
        }
      } else {
        items(
          items = schedules,
          key = Notification::id
        ) { item ->
          ScheduleItemComponent(
            content = item.content,
            date = item.noticeAt,
            onClick = { onClick(item.codePostId) },
            contentPadding = PaddingValues(horizontal = 17.dp),
            modifier = Modifier
              .animateItem()
              .fillMaxWidth()
              .height(SCHEDULE_ITEM_HEIGHT)
          )
        }
      }
    }
  }
}

@Composable
private fun ScheduleItemComponent(
  content: String,
  date: DateString,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  contentPadding: PaddingValues = PaddingValues()
) {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
      .clickable(onClick = onClick)
      .padding(contentPadding)
  ) {
    Main700Text(
      text = content,
      fontSize = 12.sp,
      lineHeight = 24.sp,
      color = MaterialTheme.colorScheme.onSurface,
      modifier = Modifier.weight(1F)
    )
    Spacer(Modifier.width(10.dp))
    Main700Text(
      text = date,
      fontSize = 12.sp,
      lineHeight = 24.sp,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )
  }
}

@Preview(showBackground = true)
@Preview(locale = "ko")
@Composable
private fun ScheduleComponentPrev() {
  AppTheme {
    Column(
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.Start,
      modifier = Modifier
        .fillMaxSize()
    ) {
      ScheduleComponent(
        schedules = List(4) { Notification.dummy(it) },
        onClick = {},
        modifier = Modifier
      )
    }
  }
}
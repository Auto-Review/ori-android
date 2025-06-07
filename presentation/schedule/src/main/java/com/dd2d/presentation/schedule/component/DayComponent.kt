package com.dd2d.presentation.schedule.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.main_text.Main600Text
import com.dd2d.core.presentation.theme.tp

@Composable
internal fun BoxScope.DayComponent(
  dayText: String,
  isSelected: Boolean,
  hasSchedule: Boolean,
  selectedDayTextColor: Color,
  scheduleOnDayColor: Color,
  dayColor: Color,
  selectedDayBackgroundShape: Shape,
  selectedDayBackground: Color,
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  Box(
    contentAlignment = Alignment.Center,
    modifier = modifier
      .align(Alignment.Center)
      .aspectRatio(1F)
      .fillMaxWidth()
      .padding(5.dp)
      .clip(selectedDayBackgroundShape)
      .clickable(onClick = onClick)
      .background(color = if (isSelected) selectedDayBackground else Color.Transparent)
  ) {
    Main600Text(
      text = dayText,
      color = when {
        isSelected -> selectedDayTextColor
        hasSchedule -> scheduleOnDayColor
        else -> dayColor
      },
      fontSize = 18.tp,
      lineHeight = 18.tp,
    )
  }
}
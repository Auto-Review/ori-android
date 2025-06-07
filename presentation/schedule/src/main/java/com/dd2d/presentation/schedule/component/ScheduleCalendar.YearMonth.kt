package com.dd2d.presentation.schedule.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import java.time.YearMonth

@Composable
internal fun YearMonth(
  yearMonth: YearMonth,
  modifier: Modifier = Modifier
) {
  Box(
    contentAlignment = Alignment.Center,
    modifier = modifier
  ) {
    Text(text = yearMonth.label, fontSize = 14.sp)
  }
}

private val YearMonth.label: String
  get() {
    val monthText = when (monthValue) {
      1 -> "January"; 2 -> "February"; 3 -> "March"
      4 -> "April"; 5 -> "May"; 6 -> "June"
      7 -> "July"; 8 -> "August"; 9 -> "September"
      10 -> "October"; 11 -> "November"; 12 -> "December"
      else -> ""
    }

    return "$monthText $year"
  }

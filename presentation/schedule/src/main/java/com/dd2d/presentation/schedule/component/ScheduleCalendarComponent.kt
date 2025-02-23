package com.dd2d.presentation.schedule.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.icon.NextButton
import com.dd2d.core.presentation.icon.PrevButton
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.schedule.model.ScheduleOnDay
import com.dd2d.domain.schedule.model.ScheduleOnMonth
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth

@Composable
internal fun ScheduleCalendarComponent(
    scheduleOnMonth: ScheduleOnMonth?,
    onDayClick: (day: LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    initialMonth: YearMonth = YearMonth.now(),
    background: Color = MaterialTheme.colorScheme.background,
    selectedDayBackgroundShape: Shape = CircleShape,
    selectedDayBackground: Color = MaterialTheme.colorScheme.primary,
    selectedDayTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    scheduleOnDayColor: Color = MaterialTheme.colorScheme.primary,
    dayColor: Color = Color(0xFF4A5660),
    dateCache: Long = 10L,
) {
    val scope = rememberCoroutineScope()

    var current by remember { mutableStateOf(initialMonth) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val scheduleDays = remember { scheduleOnMonth?.items?.map(ScheduleOnDay::date)?: emptyList() }

    val calendarState = rememberCalendarState(
        firstVisibleMonth = initialMonth,
        startMonth = initialMonth.minusMonths(dateCache),
        endMonth = initialMonth.plusMonths(dateCache),
        firstDayOfWeek = firstDayOfWeekFromLocale()
    )

    LaunchedEffect(Unit) {
        snapshotFlow { calendarState.lastVisibleMonth }
            .collect { last ->
                current = last.yearMonth
                if(current.minusMonths(dateCache/2) == calendarState.startMonth) {
                    calendarState.startMonth = calendarState.startMonth.minusMonths(dateCache)
                }
                if(current.plusMonths(dateCache/2) == calendarState.endMonth) {
                    calendarState.endMonth = calendarState.endMonth.plusMonths(dateCache)
                }
            }
    }

    Surface(
        color = background,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
        ) {
            HorizontalCalendar(
                state = calendarState,
                monthHeader = {month ->
                    Text(
                        text = getYearMonthText(year = month.yearMonth.year, month = month.yearMonth.monthValue),
                        lineHeight = 40.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    WeekComponent(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp))
                },
                dayContent = { day ->
                    DayComponent(
                        dayText = "${day.date.dayOfMonth}",
                        isSelected = selectedDate == day.date,
                        hasSchedule = day.date in scheduleDays,
                        selectedDayTextColor = selectedDayTextColor,
                        scheduleOnDayColor = scheduleOnDayColor,
                        dayColor = dayColor,
                        selectedDayBackgroundShape = selectedDayBackgroundShape,
                        selectedDayBackground = selectedDayBackground,
                        onClick = {
                            selectedDate = day.date
                            onDayClick(day.date)
                        },
                        modifier = Modifier.graphicsLayer {
                            alpha = if(day.position != DayPosition.MonthDate) 0.5F else 1F
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            PrevButton(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(40.dp)
            ) {
                scope.launch {
                    calendarState.animateScrollToMonth(
                        month = current.minusMonths(1)
                    )
                }
            }

            NextButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(40.dp)
            ) {
                scope.launch {
                    calendarState.animateScrollToMonth(
                        month = current.plusMonths(1)
                    )
                }
            }
        }
    }
}

private fun getYearMonthText(year: Int, month: Int): String {
    val monthText = when(month) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> ""
    }

    return "$monthText $year"
}

@Preview
@Preview(locale = "ko")
@Composable
private fun ScheduleCalendarComponentPrev() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
        ) {
            ScheduleCalendarComponent(
                scheduleOnMonth = ScheduleOnMonth.dummy(),
                onDayClick = {},
                modifier = Modifier
            )
        }
    }
}
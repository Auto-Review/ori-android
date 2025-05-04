package com.dd2d.presentation.schedule.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import com.dd2d.core.presentation.icon.NextButton
import com.dd2d.core.presentation.icon.PrevButton
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.notification.model.Notification
import com.dd2d.presentation.schedule.model.ScheduleOnYearMonth
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.yearMonth
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue

@Composable
internal fun ScheduleCalendar(
    schedules: SnapshotStateMap<YearMonth, ScheduleOnYearMonth>,
    onDayClick: (day: LocalDate) -> Unit,
    onYearMonthChange: (yearMonth: YearMonth) -> Unit,
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

    val calendarState = rememberCalendarState()

    LaunchedEffect(calendarState) {
        snapshotFlow { calendarState.layoutInfo.visibleMonthsInfo }
            .collect { visibleMonths ->
                val leftMonth = visibleMonths.first().month.yearMonth
                val rightMonth = visibleMonths.last().month.yearMonth

                if(leftMonth == rightMonth) {
                    current = leftMonth
                    onYearMonthChange(leftMonth)
                }

                /** 현재 (스와이프 중인)캘린더의 왼쪽에서 보이는 날짜와 `CalendarState`에 설정한 최소 월의 차이가 3개월 미만인 경우
                 *  `CalendarState`의 최소 월을 현재 최소값의 [dateCache]개월 전으로 재설정
                 *
                 *  예를 들어 최소 월이 `25년 1월`이고 현재 왼쪽에 보이는 월이 `3월`인 경우, 이 두 월의 차이가 `3 미만`이므로 최소 월을 `24년 1월`로 설정*/
                val betweenStartAndLeft = ChronoUnit.MONTHS.between(calendarState.startMonth, leftMonth).absoluteValue
                if(betweenStartAndLeft < 3) {
                    calendarState.startMonth = calendarState.startMonth.minusMonths(dateCache)
                }

                /**  `betweenStartAndLeft`에 적용된 논리와 같음. 해당 부분은 최대 월을 `늘리는 방향`으로 진행*/
                val betweenEndAndRight = ChronoUnit.MONTHS.between(rightMonth, calendarState.endMonth).absoluteValue
                if(betweenEndAndRight < 3) {
                    calendarState.endMonth = calendarState.endMonth.plusMonths(dateCache)
                }
            }
    }
    BoxWithConstraints(modifier = modifier.background(color = background)) {
        val dayComponentSize = remember { DpSize(width = maxWidth/7, height = maxWidth/7) }

        HorizontalCalendar(
            state = calendarState,
            monthHeader = { month ->
                val commonModifier = Modifier.fillMaxWidth().height(dayComponentSize.height)
                YearMonth(yearMonth = month.yearMonth, modifier = commonModifier)
                Week(modifier = commonModifier)
            },
            dayContent = { day ->
                DayComponent(
                    dayText = "${day.date.dayOfMonth}",
                    isSelected = selectedDate == day.date,
                    hasSchedule = schedules[day.date.yearMonth]?.hasScheduleAt(day.date.dayOfMonth) == true,
                    selectedDayTextColor = selectedDayTextColor,
                    scheduleOnDayColor = scheduleOnDayColor,
                    dayColor = dayColor,
                    selectedDayBackgroundShape = selectedDayBackgroundShape,
                    selectedDayBackground = selectedDayBackground,
                    onClick = {
                        selectedDate = day.date
                        onDayClick(day.date)
                    },
                    modifier = Modifier
                        .graphicsLayer {
                            alpha = if (day.position != DayPosition.MonthDate) 0.5F else 1F
                        }
                        .size(dayComponentSize)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(dayComponentSize.height * 8)
        )

        PrevButton(modifier = Modifier
            .align(Alignment.TopStart)
            .height(dayComponentSize.height)) {
            scope.launch {
                calendarState.animateScrollToMonth(
                    month = current.minusMonths(1)
                )
            }
        }

        NextButton(modifier = Modifier
            .align(Alignment.TopEnd)
            .height(dayComponentSize.height)) {
            scope.launch {
                calendarState.animateScrollToMonth(
                    month = current.plusMonths(1)
                )
            }
        }
    }
}



@Preview
@Preview(locale = "ko")
@Composable
private fun ScheduleCalendarComponentPrev() {
    val initialMonth = YearMonth.now()
    val s = remember {
        mutableStateMapOf(
            initialMonth to ScheduleOnYearMonth(initialMonth, List(3) { Notification.dummy(it) })
        )
    }
    AppTheme {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
        ) {
            ScheduleCalendar(
                schedules = s,
                onDayClick = {},
                onYearMonthChange = {},
                modifier = Modifier
            )
        }
    }
}
package com.dd2d.presentation.schedule.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.notification.model.Notification
import com.dd2d.presentation.schedule.component.BannerComponent
import com.dd2d.presentation.schedule.component.ScheduleCalendar
import com.dd2d.presentation.schedule.component.ScheduleComponent
import com.dd2d.presentation.schedule.model.ScheduleOnYearMonth
import com.kizitonwose.calendar.core.yearMonth
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ScheduleScreenContent(
    schedules: SnapshotStateMap<YearMonth, ScheduleOnYearMonth>,
    onYearMonthChange: (YearMonth) -> Unit,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier
) {
    var selectedDateSchedules by remember { mutableStateOf<List<Notification>>(emptyList()) }
    val refreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        state = refreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                state = refreshState,
                color = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.background
            )
        },
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(bottom = 14.dp)
        ) {
            BannerComponent()
            ScheduleCalendar(
                schedules = schedules,
                onDayClick = { date ->
                    selectedDateSchedules = schedules[date.yearMonth]?.onDay(date.dayOfMonth)?: emptyList()
                },
                onYearMonthChange = onYearMonthChange,
                modifier = Modifier
                    .padding(top = 15.dp, start = 27.dp, end = 27.dp)
            )
            ScheduleComponent(
                schedules = selectedDateSchedules,
                modifier = Modifier.padding(top = 20.dp, start = 27.dp, end = 27.dp)
            )
        }
    }
}



@Preview
@Preview(locale = "ko")
@Composable
private fun ScheduleScreenContentPrev() {
    val initialMonth = YearMonth.now()
    val s = remember {
        mutableStateMapOf(
            initialMonth to ScheduleOnYearMonth(initialMonth, List(3) { Notification.dummy(it) })
        )
    }
    AppTheme {
        ScheduleScreenContent(
            schedules = s,
            onYearMonthChange = {},
            onRefresh = {},
            isRefreshing = false,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
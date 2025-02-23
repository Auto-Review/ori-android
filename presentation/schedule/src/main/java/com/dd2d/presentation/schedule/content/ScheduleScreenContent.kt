package com.dd2d.presentation.schedule.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.schedule.model.ScheduleOnMonth
import com.dd2d.presentation.schedule.component.BannerComponent
import com.dd2d.presentation.schedule.component.ScheduleCalendarComponent
import com.dd2d.presentation.schedule.component.ScheduleComponent

@Composable
internal fun ScheduleScreenContent(
    scheduleOnMonth: ScheduleOnMonth?,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
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
            ScheduleCalendarComponent(
                scheduleOnMonth = scheduleOnMonth,
                onDayClick = {},
                modifier = Modifier
                    .heightIn(min = 400.dp)
                    .padding(top = 15.dp, start = 27.dp, end = 27.dp)
            )
            ScheduleComponent(scheduleOnDayList = scheduleOnMonth?.items?: emptyList(), modifier = Modifier.padding(top = 15.dp, start = 27.dp, end = 27.dp))
        }
    }
}



@Preview
@Preview(locale = "ko")
@Composable
private fun ScheduleScreenContentPrev() {
    AppTheme {
        ScheduleScreenContent(
            scheduleOnMonth = ScheduleOnMonth.dummy(),
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
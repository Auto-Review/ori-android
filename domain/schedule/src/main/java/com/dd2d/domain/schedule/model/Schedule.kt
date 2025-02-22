package com.dd2d.domain.schedule.model

import java.time.LocalDate

data class Schedule(
    val date: LocalDate,
    val items: List<ScheduleItem>,
)

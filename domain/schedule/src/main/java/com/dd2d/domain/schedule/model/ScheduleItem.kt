package com.dd2d.domain.schedule.model

import java.time.LocalDate

data class ScheduleItem(
    val id: Int,
    val title: String,
    val date: LocalDate,
)

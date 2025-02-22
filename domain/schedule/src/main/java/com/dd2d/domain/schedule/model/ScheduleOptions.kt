package com.dd2d.domain.schedule.model

import java.time.LocalDate

data class ScheduleOptions(
    val from: LocalDate = LocalDate.now().withDayOfMonth(1),
    val to: LocalDate = with(LocalDate.now()) { withDayOfMonth(month.length(isLeapYear)) }
)

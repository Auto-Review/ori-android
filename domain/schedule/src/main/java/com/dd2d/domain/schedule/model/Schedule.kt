package com.dd2d.domain.schedule.model

import org.jetbrains.annotations.Async
import java.time.LocalDate

data class Schedule(
    val date: LocalDate,
    val items: List<ScheduleItem>,
) {
    companion object {
        fun dummy(size: Int = 30) = Schedule(
            date = LocalDate.now(),
            items = List(size) { ScheduleItem.dummy(it) },
        )
    }
}

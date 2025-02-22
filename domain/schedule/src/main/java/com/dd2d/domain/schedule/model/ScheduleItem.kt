package com.dd2d.domain.schedule.model

import java.time.LocalDate

data class ScheduleItem(
    val id: Int,
    val title: String,
    val date: LocalDate,
) {
    companion object {
        fun dummy(id: Int = 1) = ScheduleItem(
            id = id,
            title = "일정 - $id",
            date = LocalDate.now(),
        )
    }
}

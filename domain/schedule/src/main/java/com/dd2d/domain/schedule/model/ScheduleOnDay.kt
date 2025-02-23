package com.dd2d.domain.schedule.model

import java.time.LocalDate

/** [date]일의 일정. 예를 들어 [date] 값이 `2025-02-23`일 때 `23일`에 해당하는 일정을 의미.
 *
 * @property id 일정 아이디
 * @property title 일정 제목
 * @property date 일정 날짜
 *
 * @see ScheduleOnMonth
 */
data class ScheduleOnDay(
    val id: Int,
    val title: String,
    val date: LocalDate,
) {
    companion object {
        fun dummy(id: Int = 1) = ScheduleOnDay(
            id = id,
            title = "일정 - $id",
            date = LocalDate.now(),
        )
    }
}

package com.dd2d.domain.schedule.model

import java.time.LocalDate

/** [date]월의 일정. 예를 들어 [date] 값이 `2025-02-23`일 때 `2우러`에 해당하는 일정을 의미.
 *
 * @property date 일정 날짜
 * @property items 날짜에 속한 일 단위 일정 목록. [ScheduleOnDay]
 *
 * @see ScheduleOnDay
 */
data class ScheduleOnMonth(
    val date: LocalDate,
    val items: List<ScheduleOnDay>,
) {
    companion object {
        fun dummy(size: Int = 30) = ScheduleOnMonth(
            date = LocalDate.now(),
            items = List(size) { ScheduleOnDay.dummy(it) },
        )
    }
}

package com.dd2d.presentation.schedule.model

import com.dd2d.core.core.util.dateStringToLocalDate
import com.dd2d.domain.notification.model.Notification
import java.time.YearMonth

/** [yearMonth]에 대응되는 일정(=[schedules]) 모델 */
internal data class ScheduleOnYearMonth(
  val yearMonth: YearMonth,
  val schedules: List<Notification>
) {
  /** 일별 일정. [schedules]을 `yyyy-MM-dd` 중 `MM`으로 그룹핑한 데이터. */
  private val groupByDay = schedules
    .groupBy { it.noticeAt.dateStringToLocalDate("yyyy-MM-dd").dayOfMonth }

  /** [day]에 일정이 있는 경우 `true`반환 */
  fun hasScheduleAt(day: Int): Boolean = groupByDay[day] != null

  /** [day]의 일정을 반환 */
  fun onDay(day: Int): List<Notification> = groupByDay[day] ?: emptyList()
}
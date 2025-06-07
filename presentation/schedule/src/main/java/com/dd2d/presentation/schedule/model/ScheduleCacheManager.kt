package com.dd2d.presentation.schedule.model

import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.onStateSuccess
import com.dd2d.domain.notification.model.Notification
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import java.time.YearMonth
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue

/** 일정 캐시 관리 클래스 */
internal class ScheduleCacheManager {

  /** 새로운 일정을 받아오는 flow를 반환. 이미 저장된 날짜의 데이터는 불러오지 않음
   *
   * @param yearMonth 현재 날짜
   * @param schedules 현재 일정 목록
   * @param getSchedule 새로운 일정을 받아오는 flow
   * @param preloadSize 미리 로드할 일정 개수. 현재일 기준 +-로 로드함. Ex> preloadSize = 2 -> -2~+2 만큼 로드
   * */
  @OptIn(ExperimentalCoroutinesApi::class)
  fun scheduleCacheTaskFlow(
    yearMonth: StateFlow<YearMonth>,
    schedules: SnapshotStateMap<YearMonth, ScheduleOnYearMonth>,
    getSchedule: (YearMonth) -> Flow<DataState<List<Notification>>>,
    preloadSize: Long = 2L,
  ): Flow<DataState<List<Notification>>> = yearMonth
    .map { center ->
      (-preloadSize..preloadSize)
        .map { offset -> center.plusMonths(offset) }
        .filterNot { schedules.containsKey(it) }
    }
    .flatMapLatest { monthsToLoad ->
      monthsToLoad
        .map { month ->
          getSchedule(month).onStateSuccess {
            schedules[month] = ScheduleOnYearMonth(month, this)
          }
        }
        .merge()
    }

  /** 캐시된 데이터를 관리하는 flow를 반환.
   * @param yearMonth 현재 날짜
   * @param schedules 현재 일적 목록
   * @param maxSize 캐싱 데이터 최대 개수
   * @param cacheMonth 캐시할 데이터의 기준점. 현재일과 캐시된 데이터의 날짜의 차이가 [cacheMonth]보다 큰 경우 제거 대상
   * */
  fun cachedScheduleManageTaskFlow(
    yearMonth: StateFlow<YearMonth>,
    schedules: SnapshotStateMap<YearMonth, ScheduleOnYearMonth>,
    maxSize: Int = 12,
    cacheMonth: Long = 5L,
  ): Flow<Int> {
    return snapshotFlow { schedules.size }
      .onEach { size ->
        if (size > maxSize) {
          schedules.keys.forEach { cached ->
            if (ChronoUnit.MONTHS.between(cached, yearMonth.value).absoluteValue > cacheMonth) {
              schedules.remove(cached)
            }
          }
        }
      }
  }
}
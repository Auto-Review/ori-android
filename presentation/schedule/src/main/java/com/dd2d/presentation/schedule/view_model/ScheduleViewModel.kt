package com.dd2d.presentation.schedule.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.core.state.onStateLoading
import com.dd2d.core.core.state.onStateSuccess
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.domain.notification.repository.NotificationRepository
import com.dd2d.presentation.schedule.model.ScheduleCacheManager
import com.dd2d.presentation.schedule.model.ScheduleOnYearMonth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
internal class ScheduleViewModel @Inject constructor(
  private val notificationRepository: NotificationRepository,
) : ViewModel(), UIStateManager {
  override val uiState = MutableStateFlow<UIState>(UIState.Idle)

  val schedules = mutableStateMapOf<YearMonth, ScheduleOnYearMonth>()

  private val yearMonth = MutableStateFlow(YearMonth.now())
  fun updateYearMonth(value: YearMonth) = yearMonth.update { value }

  var isRefreshing by mutableStateOf(false); private set
  fun refresh() {
    notificationRepository.getMyNotificationOnDate(yearMonth.value)
      .onStateLoading { isRefreshing = true }
      .onStateSuccess {
        schedules[yearMonth.value] = ScheduleOnYearMonth(yearMonth.value, this)
        isRefreshing = false
      }
      .launchIn(viewModelScope)
  }

  init {
    val manager = ScheduleCacheManager()
    manager.scheduleCacheTaskFlow(
      yearMonth,
      schedules,
      notificationRepository::getMyNotificationOnDate
    ).launchIn(viewModelScope)
    manager.cachedScheduleManageTaskFlow(yearMonth, schedules).launchIn(viewModelScope)
  }
}
package com.dd2d.domain.notification.repository

import com.dd2d.core.core.state.DataState
import com.dd2d.domain.notification.model.Notification
import kotlinx.coroutines.flow.Flow
import java.time.YearMonth

interface NotificationRepository {
  fun getMyNotifications(): Flow<DataState<List<Notification>>>
  fun getMyNotificationOnDate(yearMonth: YearMonth): Flow<DataState<List<Notification>>>
  suspend fun toggleNotification(id: Int): Result<Unit>
}
package com.dd2d.data.notification.repository

import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.asDataState
import com.dd2d.data.notification.mapper.toNotification
import com.dd2d.data_source.remote.server.notification.NotificationApi
import com.dd2d.data_source.remote.server.notification.dto.response.NotificationResponseDto
import com.dd2d.domain.notification.model.Notification
import com.dd2d.domain.notification.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.YearMonth
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationAPI: NotificationApi,
): NotificationRepository {
    override fun getMyNotifications(): Flow<DataState<List<Notification>>> = flow {
        val response = notificationAPI
            .getMyNotificationList()

        emit(response.map(NotificationResponseDto::toNotification))
    }.asDataState()

    override fun getMyNotificationOnDate(yearMonth: YearMonth): Flow<DataState<List<Notification>>> = flow {
        val response = notificationAPI
            .getMyNotificationOnDate(year = yearMonth.year, month = yearMonth.monthValue)

        emit(response.map(NotificationResponseDto::toNotification))
    }.asDataState()

    override suspend fun toggleNotification(id: Int): Result<Unit> {
        return notificationAPI.runCatching { toggleNotificationCheckState(notificationId = id) }
            .map { /** do nothing */ }
    }
}
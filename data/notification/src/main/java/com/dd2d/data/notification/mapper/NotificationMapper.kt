package com.dd2d.data.notification.mapper

import com.dd2d.data_source.remote.server.notification.dto.response.NotificationResponseDto
import com.dd2d.domain.notification.model.Notification

internal fun NotificationResponseDto.toNotification(): Notification {
  return Notification(
    id = this.id,
    codePostId = this.codePostId,
    content = this.content,
    noticeAt = this.executeTime,
    checked = this.checked,
    state = Notification.State.valueOf(this.status),
  )
}
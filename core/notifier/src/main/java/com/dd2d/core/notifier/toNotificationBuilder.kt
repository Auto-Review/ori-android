package com.dd2d.core.notifier

import android.content.Context
import androidx.core.app.NotificationCompat

internal fun NotifyScope.toNotificationBuilder(
  context: Context,
  channelId: String
): NotificationCompat.Builder {
  require(iconRes != -1) { "아이콘을 설정해주세요." }

  return NotificationCompat.Builder(context, channelId)
    .setContentTitle(title)
    .setContentText(content)
    .setSmallIcon(iconRes)
    .setContentIntent(pendingIntent)
    .setAutoCancel(autoCancel)
    .setGroupSummary(groupSummary)
    .setGroup(groupKey)
    .setPriority(priority)
    .setStyle(style)
    .setVibrate(vibrationPattern?.toLongArray() ?: longArrayOf())
    .setSound(soundUri)
    .setLargeIcon(largeIcon)
    .setColor(color)
    .setSilent(isSilent)
    .setProgress(progressMax, progressCurrent, progressIndeterminate)
    .setSubText(subText)
    .setTicker(ticker)
    .setOnlyAlertOnce(onlyAlertOnce)
    .setOngoing(ongoing)
    .also { builder ->
      actions.forEach(builder::addAction)
    }
}
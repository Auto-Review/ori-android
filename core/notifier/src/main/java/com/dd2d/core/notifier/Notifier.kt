package com.dd2d.core.notifier

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationManagerCompat

object Notifier {
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun notify(
        context: Context,
        channelId: String,
        notificationId: Int = System.currentTimeMillis().toInt(),
        block: NotifyScope.() -> Unit
    ): Notification {
        val manager = NotificationManagerCompat.from(context)
        val scope = NotifyScopeImpl().apply(block)
        val builder = scope.toNotificationBuilder(context, channelId)
        return builder.build().also { notification ->
            manager.notify(notificationId, notification)
        }
    }

    fun createChannelOnce(
        context: Context,
        channelId: String,
        channelName: String,
        channelImportance: ChannelImportance = ChannelImportance.DEFAULT,
    ) {
        val manager = NotificationManagerCompat.from(context)

        if(manager.getNotificationChannel(channelId) == null) {
            val channel = NotificationChannel(channelId, channelName, channelImportance.value)
            manager.createNotificationChannel(channel)
        }
    }

    enum class ChannelImportance(internal val value: Int) {
        DEFAULT(value = NotificationManager.IMPORTANCE_DEFAULT),
        HIGH(value = NotificationManager.IMPORTANCE_HIGH),
        LOW(value = NotificationManager.IMPORTANCE_LOW),
        MAX(value = NotificationManager.IMPORTANCE_MAX),
        MIN(value = NotificationManager.IMPORTANCE_MIN),
    }
}
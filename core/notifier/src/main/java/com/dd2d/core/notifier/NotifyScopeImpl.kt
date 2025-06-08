package com.dd2d.core.notifier

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.core.app.NotificationCompat

internal data class NotifyScopeImpl(
  override var title: String = "",
  override var content: String = "",
  override var iconRes: Int = -1,
  override var pendingIntent: PendingIntent? = null,
  override val actions: MutableList<NotificationCompat.Action> = mutableListOf(),
  override var autoCancel: Boolean = true,
  override var groupSummary: Boolean = false,
  override var groupKey: String = "",
  override var priority: Int = NotificationCompat.PRIORITY_DEFAULT,
  override var style: NotificationCompat.Style? = null,
  override var vibrationPattern: List<Long>? = null,
  override var soundUri: Uri? = null,
  override var largeIcon: Bitmap? = null,
  override var color: Int = Color.TRANSPARENT,
  override var isSilent: Boolean = false,
  override var progressMax: Int = 0,
  override var progressCurrent: Int = 0,
  override var progressIndeterminate: Boolean = false,
  override var subText: String? = null,
  override var ticker: String? = null,
  override var onlyAlertOnce: Boolean = false,
  override var ongoing: Boolean = false,
) : NotifyScope {
  private val defaultFlag = PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
  override fun createActivityPendingIntent(
    context: Context,
    targetActivity: Class<out Activity>,
    requestCode: Int,
    intentBuilder: Intent.() -> Unit
  ): PendingIntent {
    val intent = Intent(context, targetActivity)
      .apply {
        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
            Intent.FLAG_ACTIVITY_NEW_TASK
      }
      .apply(intentBuilder)

    return PendingIntent.getActivity(context, requestCode, intent, defaultFlag)
  }

  override fun createServicePendingIntent(
    context: Context,
    targetActivity: Class<out Activity>,
    requestCode: Int,
    intentBuilder: Intent.() -> Unit
  ): PendingIntent {
    val intent = Intent(context, targetActivity)
      .apply {
        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
            Intent.FLAG_ACTIVITY_NEW_TASK
      }
      .apply(intentBuilder)

    return PendingIntent.getService(context, requestCode, intent, defaultFlag)
  }

  override fun createBroadcastPendingIntent(
    context: Context,
    targetActivity: Class<out Activity>,
    requestCode: Int,
    intentBuilder: Intent.() -> Unit
  ): PendingIntent {
    val intent = Intent(context, targetActivity)
      .apply {
        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
            Intent.FLAG_ACTIVITY_NEW_TASK
      }
      .apply(intentBuilder)

    return PendingIntent.getBroadcast(context, requestCode, intent, defaultFlag)
  }
}
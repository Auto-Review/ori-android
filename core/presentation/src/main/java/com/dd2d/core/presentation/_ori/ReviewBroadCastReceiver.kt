package com.dd2d.core.presentation._ori

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.dd2d.core.notifier.Notifier
import com.dd2d.core.presentation.R
import com.dd2d.core.presentation.permission.isNotificationPermissionGranted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject
import kotlin.math.absoluteValue

@Parcelize
sealed class ReviewTarget(open val id: Int, open val title: String): Parcelable {
  class CodePost(override val id: Int, override val title: String): ReviewTarget(id, title)
  class TIL(override val id: Int, override val title: String): ReviewTarget(id, title)
}

@Parcelize
data class ReviewAlarmData(
  val target: ReviewTarget,
  val title: String,
  val content: String?,
  val date: LocalDateTime,
  @DrawableRes val iconRes: Int? = null,
): Parcelable {
  companion object {
    internal const val EXTRA = "review_alarm_data"
    fun fromIntent(intent: Intent): ReviewAlarmData? {
      return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getParcelableExtra(EXTRA, ReviewAlarmData::class.java)
      } else {
        intent.getParcelableExtra(EXTRA)
      }
    }
  }
  val requestCode: Int get() = date.toString().hashCode().absoluteValue
}

fun Context.registerReviewAlarm(target: ReviewTarget, reviewDate: LocalDateTime) {
  val data = ReviewAlarmData(
    target = target,
    title = "리뷰 시간이에요.",
    content = "[${target.title}] 함께 봐요!",
    date = reviewDate,
    iconRes = R.drawable.review_alarm
  )
  val alarmManager = getSystemService(AlarmManager::class.java)
  val triggerTime = data.date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
  val intent = Intent(this, ReviewBroadCastReceiver::class.java)
    .apply {
      action = ReviewBroadCastReceiver.REVIEW_ALARM
      putExtra(ReviewAlarmData.EXTRA, data)
    }
  val pendingIntent = PendingIntent.getBroadcast(
    this,
    data.requestCode,
    intent,
    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
  )

  alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
}

private const val REVIEW_CHANNEL_ID = "review_channel_id"
private const val REVIEW_GROUP_KEY = "review_group_key"

@AndroidEntryPoint
@SuppressLint("MissingPermission")
class ReviewBroadCastReceiver: BroadcastReceiver() {
  companion object {
    const val REVIEW_ALARM = "review_alarm"
  }
  @Inject lateinit var appStartingPointProvider: AppStartingPointProvider

  override fun onReceive(context: Context?, intent: Intent?) {
    if(context == null) return
    if(intent == null) return

    if(!context.isNotificationPermissionGranted()) return

    val data = ReviewAlarmData.fromIntent(intent)?: return

    Notifier.createChannelOnce(context, REVIEW_CHANNEL_ID, "리뷰 알림", Notifier.ChannelImportance.HIGH)

    notify(context, data)
    summaryNotify(context, data)
  }

  private fun notify(context: Context, data: ReviewAlarmData) {
    Notifier.notify(context = context, channelId = REVIEW_CHANNEL_ID, notificationId = data.requestCode) {
      title = data.title
      content = data.content?: ""
      iconRes = data.iconRes?: R.drawable.review_alarm

      groupKey = REVIEW_GROUP_KEY
      pendingIntent = createActivityPendingIntent(
        context = context,
        targetActivity = appStartingPointProvider.getStartingPoint(),
        requestCode = data.requestCode,
        intentBuilder = {
          putExtra(ReviewAlarmData.EXTRA, data)
        }
      )
    }
  }

  private fun summaryNotify(context: Context, data: ReviewAlarmData) {
    Notifier.notify(context = context, channelId = REVIEW_CHANNEL_ID, notificationId = 1001) {
      title = "리뷰 알림"
      iconRes = data.iconRes?: R.drawable.review_alarm

      groupKey = REVIEW_GROUP_KEY
      groupSummary = true
      style = NotificationCompat.InboxStyle()
        .setSummaryText("리뷰 알림")
      pendingIntent = createActivityPendingIntent(
        context = context,
        targetActivity = appStartingPointProvider.getStartingPoint(),
        requestCode = 1001,
        intentBuilder = {}
      )
    }
  }
}
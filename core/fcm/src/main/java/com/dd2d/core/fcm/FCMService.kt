package com.dd2d.core.fcm

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.Constants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FCMService : FirebaseMessagingService() {
  override fun onNewToken(token: String) {
    super.onNewToken(token)
    CoroutineScope(Dispatchers.Default).launch { FCMModule.tokenObserver.emit(token) }
  }

  override fun onMessageReceived(message: RemoteMessage) {
    super.onMessageReceived(message)
    message.log()
  }

  /** 앱이 백그라운드에 있을 때 `notification` 키 값이 있으면 [onMessageReceived] 호출이 안됨.
   * [handleIntent]를 통해 `notification` 키 값을 제거하여 정상적으로 [onMessageReceived]가 호출되도록 함.  */
  override fun handleIntent(intent: Intent?) {
    val extras = intent?.extras ?: return

    val key = Constants.MessageNotificationKeys.ENABLE_NOTIFICATION
    extras.remove(key)
    if (key.startsWith(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX)) {
      val oldKey = key.replace(
        Constants.MessageNotificationKeys.NOTIFICATION_PREFIX,
        Constants.MessageNotificationKeys.NOTIFICATION_PREFIX_OLD
      )
      extras.remove(oldKey)
    }

    super.handleIntent(intent.replaceExtras(extras))
  }
}

fun RemoteMessage.log() {
  Log.d(
    "LOG_CHECK", "log: message" +
        "\ndata : ${this.data.entries.joinToString { "${it.key} : ${it.value}" }} " +
        "\nnoti.body: ${this.notification?.body}" +
        "\nnoti.title : ${this.notification?.title}" +
        ""
  )
}
package com.dd2d.core.fcm

import android.util.Log
import com.google.firebase.messaging.BuildConfig
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FCMService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        if(BuildConfig.DEBUG) { Log.d("LOG_CHECK", "onNewToken: token: $token") }
        CoroutineScope(Dispatchers.Default).launch { FCMModule.tokenObserver.emit(token) }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.log()
    }
}

fun RemoteMessage.log() {
    Log.d("LOG_CHECK", "log: message\n" +
            this.data.values.joinToString() +
            "${this.notification?.body}" +
            "${this.notification?.title}" +
            "")
}
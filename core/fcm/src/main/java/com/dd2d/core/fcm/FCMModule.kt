package com.dd2d.core.fcm

import android.content.Context
import com.google.android.datatransport.runtime.dagger.Module
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Module
object FCMModule {
    fun init(context: Context) { FirebaseApp.initializeApp(context) }
    val tokenObserver = MutableSharedFlow<String>(replay = 1)
    suspend fun getFCMToken(): String = suspendCancellableCoroutine { conti ->
        FirebaseMessaging.getInstance().token
            .addOnSuccessListener { token -> conti.resume(token) }
            .addOnCanceledListener { conti.cancel() }
            .addOnFailureListener { conti.resumeWithException(it) }
    }
}
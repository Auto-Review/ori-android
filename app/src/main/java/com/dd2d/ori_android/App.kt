package com.dd2d.ori_android

import android.app.Application
import com.dd2d.core.fcm.FCMModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        FCMModule.init(this@App)
    }
}
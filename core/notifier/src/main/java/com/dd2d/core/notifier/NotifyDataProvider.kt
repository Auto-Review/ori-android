package com.dd2d.core.notifier

import android.app.Activity

interface NotifyDataProvider {
	fun provideAppStartPoint(): Class<out Activity>
	fun provideNotificationIconRes(): Int
}
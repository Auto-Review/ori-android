package com.dd2d.core.presentation.permission

import android.Manifest.permission
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

fun Context.isNotificationPermissionGranted(): Boolean {
  if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return true

  val permission = permission.POST_NOTIFICATIONS
  return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}
package com.dd2d.core.presentation._ori

import androidx.activity.ComponentActivity

interface AppStartingPointProvider {
  fun getStartingPoint(): Class<out ComponentActivity>
}
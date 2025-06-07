package com.dd2d.core.data_store_manager

import androidx.datastore.preferences.core.intPreferencesKey

object Keys {
  fun getAuthStateKey() = intPreferencesKey(name = "auth_state")
}
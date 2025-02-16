package com.dd2d.core.data_store

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey


interface DataStoreManager {
    suspend fun <T> setValueByKey(key: Preferences.Key<T>, value: T)
    suspend fun <T> getValueByKey(key: Preferences.Key<T>): T?
    suspend fun <T> removeValueByKey(vararg keys: Preferences.Key<T>)

    object Key {
        val accessTokenKey = stringPreferencesKey("access_token")
    }
}

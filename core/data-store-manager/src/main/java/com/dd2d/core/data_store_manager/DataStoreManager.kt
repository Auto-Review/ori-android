package com.dd2d.core.data_store_manager

import androidx.datastore.preferences.core.Preferences


interface DataStoreManager {
    suspend fun <T> saveValueByKey(key: Preferences.Key<T>, value: T)
    suspend fun <T> getValueByKey(key: Preferences.Key<T>): T?

    suspend fun <T> getValueByKey(key: Preferences.Key<T>, default: T): T

    suspend fun <T> removeValueByKey(vararg keys: Preferences.Key<T>)
}
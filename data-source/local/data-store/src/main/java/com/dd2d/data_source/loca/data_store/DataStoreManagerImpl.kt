package com.dd2d.data_source.loca.data_store

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.dd2d.core.data_store.DataStoreManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("data_store")

class DataStoreManagerImpl @Inject constructor(
    @ApplicationContext context: Context
): DataStoreManager {
    private val dataStore = context.dataStore

    override suspend fun <T> setValueByKey(key: Preferences.Key<T>, value: T) {
        dataStore.edit { pref ->
            pref[key] = value
        }
    }

    override suspend fun <T> getValueByKey(key: Preferences.Key<T>): T? {
        return dataStore.data
            .map { pref ->
                pref[key]
            }
            .firstOrNull()
    }

    override suspend fun <T> removeValueByKey(vararg keys: Preferences.Key<T>) {
        dataStore.edit { pref ->
            for(key in keys) {
                pref.remove(key)
            }
        }
    }
}
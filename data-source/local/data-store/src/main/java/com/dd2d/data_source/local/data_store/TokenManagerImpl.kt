package com.dd2d.data_source.local.data_store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dd2d.core.token_manager.TokenManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("token_manager")

class TokenManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
): TokenManager {
    companion object {
        private var accessToken: String? = null
    }

    private val storage = context.dataStore
    private val accessTokenKey = stringPreferencesKey(name = "access_token")
    private val refreshTokenKey = stringPreferencesKey(name = "refresh_token")

    override suspend fun getAccessToken(): String {
        return accessToken?: storage.data
            .map { pref -> pref[accessTokenKey] }
            .catch { emit("") }
            .firstOrNull() ?: ""
            .also { accessToken = it }
    }

    override suspend fun getRefreshToken(): String {
        return storage.data
            .map { pref -> pref[refreshTokenKey] }
            .catch { emit("") }
            .firstOrNull() ?: ""
    }

    override suspend fun saveAuthToken(accessToken: String, refreshToken: String?) {
        storage.edit { pref ->
            pref[accessTokenKey] = accessToken
            refreshToken?.let { refresh ->
                pref[refreshTokenKey] = refresh
            }
        }
    }
}
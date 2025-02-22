package com.dd2d.domain.local_setting.repository

interface LocalSettingRepository {
    suspend fun saveAccessToken(accessToken: String)
    suspend fun getAccessToken(): String
}
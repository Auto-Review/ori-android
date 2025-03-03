package com.dd2d.core.token_manager

interface TokenManager {
    suspend fun getAccessToken(): String
    suspend fun getRefreshToken(): String
    suspend fun saveAuthToken(accessToken: String, refreshToken: String?)
}
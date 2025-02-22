package com.dd2d.data.local_setting.repository

import com.dd2d.core.data_store_manager.DataStoreManager
import com.dd2d.domain.local_setting.repository.LocalSettingRepository
import javax.inject.Inject

class LocalSettingRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
): LocalSettingRepository {
    override suspend fun saveAccessToken(accessToken: String) {
        dataStoreManager.saveAccessToken(accessToken = accessToken)
    }

    override suspend fun getAccessToken(): String {
        return dataStoreManager.getAccessToken()
    }
}
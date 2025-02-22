package com.dd2d.data.local_setting

import com.dd2d.data.local_setting.repository.LocalSettingRepositoryImpl
import com.dd2d.domain.local_setting.repository.LocalSettingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalSettingModule {
    @Binds
    @Singleton
    abstract fun bindLocalSettingRepository(impl: LocalSettingRepositoryImpl): LocalSettingRepository
}
package com.dd2d.data_source.local.data_store

import com.dd2d.core.data_store_manager.DataStoreManager
import com.dd2d.core.token_manager.TokenManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {
    @Binds
    @Singleton
    abstract fun bindDataStoreManager(impl: DataStoreManagerImpl): DataStoreManager

    @Binds
    @Singleton
    abstract fun bindTokenManager(impl: TokenManagerImpl): TokenManager
}
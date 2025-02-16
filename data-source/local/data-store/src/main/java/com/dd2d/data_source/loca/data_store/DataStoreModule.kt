package com.dd2d.data_source.loca.data_store

import com.dd2d.core.data_store.DataStoreManager
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
}
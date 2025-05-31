package com.dd2d.data.til

import com.dd2d.data.til.repository.TILRepositoryImpl
import com.dd2d.data.til.repository.TILScrapRepositoryImpl
import com.dd2d.domain.til.repository.TILRepository
import com.dd2d.domain.til.repository.TILScrapRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TILModule {
    @Binds
    @Singleton
    abstract fun bindTILRepository(impl: TILRepositoryImpl): TILRepository

    @Binds
    @Singleton
    abstract fun bindTILScrapRepository(impl: TILScrapRepositoryImpl): TILScrapRepository
}
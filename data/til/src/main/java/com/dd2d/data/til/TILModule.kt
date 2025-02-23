package com.dd2d.data.til

import com.dd2d.data.til.repository.TILRepositoryImpl
import com.dd2d.domain.til.repository.TILRepository
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
}
package com.dd2d.data.auth_user

import com.dd2d.data.auth_user.auth.repository.AuthRepositoryImpl
import com.dd2d.domain.auth_user.auth.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthUserModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}
package com.dd2d.data.auth_user

import com.dd2d.data.auth_user.auth.repository.AuthRepositoryImpl
import com.dd2d.data.auth_user.auth.use_case.SignInUseCaseImpl
import com.dd2d.data.auth_user.user.repository.UserRepositoryImpl
import com.dd2d.domain.auth_user.auth.repository.AuthRepository
import com.dd2d.domain.auth_user.auth.use_case.SignInUseCase
import com.dd2d.domain.auth_user.user.repository.UserRepository
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
  abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

  @Binds
  @Singleton
  abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

  @Binds
  abstract fun bindSignInUseCase(impl: SignInUseCaseImpl): SignInUseCase
}
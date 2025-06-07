package com.dd2d.data.notification

import com.dd2d.data.notification.repository.NotificationRepositoryImpl
import com.dd2d.domain.notification.repository.NotificationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationModule {
  @Binds
  @Singleton
  abstract fun bindNotificationRepository(impl: NotificationRepositoryImpl): NotificationRepository
}
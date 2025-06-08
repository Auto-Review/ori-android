package com.dd2d.ori_android

import androidx.activity.ComponentActivity
import com.dd2d.core.presentation._ori.AppStartingPointProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
  @Binds
  @Singleton
  abstract fun bindAppStartingPointProvider(impl: AppStartingPointProviderImpl): AppStartingPointProvider
}

class AppStartingPointProviderImpl @Inject constructor() : AppStartingPointProvider {
  override fun getStartingPoint(): Class<out ComponentActivity> = MainActivity::class.java
}
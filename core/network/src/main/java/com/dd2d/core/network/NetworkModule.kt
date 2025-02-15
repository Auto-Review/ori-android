package com.dd2d.core.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    @Named("server_client")
    fun provideHttpClient(): HttpClient {
        return HttpClient {
            defaultRequest {
                url("http://ec2-43-200-245-141.ap-northeast-2.compute.amazonaws.com:8080")
            }
            install(ContentNegotiation) {
                json(
                    contentType = io.ktor.http.ContentType.Application.Json,
                    json = kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        coerceInputValues = true
                    }
                )
            }
        }
    }
}
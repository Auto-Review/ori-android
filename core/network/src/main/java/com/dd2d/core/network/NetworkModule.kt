package com.dd2d.core.network

import androidx.datastore.preferences.core.stringPreferencesKey
import com.dd2d.core.data_store.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    @Named("server_client")
    fun provideHttpClient(dataStoreManager: DataStoreManager): HttpClient {
        return HttpClient(OkHttp) {
            defaultRequest {
                contentType(ContentType.Application.Json)
                url("http://ec2-43-200-245-141.ap-northeast-2.compute.amazonaws.com:8080")
            }
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        coerceInputValues = true
                    }
                )
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val accessToken = dataStoreManager
                            .getValueByKey(key = stringPreferencesKey(name = "access_token"))?: ""
                        BearerTokens(accessToken = accessToken, refreshToken = null)
                    }
                }
            }
        }
    }
}
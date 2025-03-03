package com.dd2d.core.network

import com.dd2d.core.token_manager.TokenManager
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
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
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
    fun provideHttpClient(tokenManager: TokenManager): HttpClient {
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
                    refreshTokens {
                        val accessToken = tokenManager.getAccessToken()
                        val refreshToken = tokenManager.getRefreshToken()

                        val response = client.get(urlString = "/v1/api/auth/reissued") {
                            headers {
                                append(HttpHeaders.Authorization, "Bearer $accessToken")
                                append("refreshToken", refreshToken)
                            }
                        }

                        val newAccessToken = response.headers["accesstoken"]?.substringAfter(" ")
                        val newRefreshToken = response.headers["refreshtoken"]

                        newAccessToken?.let {
                            tokenManager.saveAuthToken(accessToken = newAccessToken, refreshToken = newRefreshToken)
                            BearerTokens(newAccessToken, newRefreshToken)
                        }
                    }
                }
            }
        }
    }
}
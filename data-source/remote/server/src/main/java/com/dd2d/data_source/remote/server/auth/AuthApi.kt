package com.dd2d.data_source.remote.server.auth

import com.dd2d.data_source.remote.server._common.headerHandling
import com.dd2d.data_source.remote.server.auth.dto.request.AuthRequestDto
import com.dd2d.data_source.remote.server.auth.dto.response.AuthResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject
import javax.inject.Named

class AuthApi @Inject constructor(
    @Named("server_client") private val client: HttpClient
) {
    suspend fun auth(body: AuthRequestDto): AuthResponseDto = client
        .post("/v1/api/auth/token") {
            setBody(body)
        }
        .headerHandling { headers ->
            AuthResponseDto(
                accessToken = headers["accesstoken"],
                refreshToken = headers["refreshtoken"]
            )
        }
}

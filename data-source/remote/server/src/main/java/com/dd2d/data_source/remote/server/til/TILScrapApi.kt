package com.dd2d.data_source.remote.server.til

import com.dd2d.core.token_manager.TokenManager
import com.dd2d.data_source.remote.server._common.PagingResponseDto
import com.dd2d.data_source.remote.server._common.authorizationHeader
import com.dd2d.data_source.remote.server._common.bodyHandling
import com.dd2d.data_source.remote.server.til.dto.request.TILScrapCreateRequestDto
import com.dd2d.data_source.remote.server.til.dto.response.TILListItemResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject
import javax.inject.Named

class TILScrapApi @Inject constructor(
    @Named("server_client") private val client: HttpClient,
    private val tokenManager: TokenManager,
) {
    suspend fun getMyScrapList(
        page: Int,
        size: Int,
    ): PagingResponseDto<TILListItemResponseDto> = client
        .get(urlString = "/v1/api/profile/bookmark/til") {
            authorizationHeader(token = tokenManager.getAccessToken())
            url {
                parameters.append("page", page.toString())
                parameters.append("size", size.toString())
            }
        }.bodyHandling()

    suspend fun createScrap(
        body: TILScrapCreateRequestDto,
    ): Unit = client
        .post(urlString = "/v1/api/post/til/bookmark") {
            authorizationHeader(token = tokenManager.getAccessToken())
            setBody(body)
        }
        .bodyHandling()
}
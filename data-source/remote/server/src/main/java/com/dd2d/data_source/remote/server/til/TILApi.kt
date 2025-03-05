package com.dd2d.data_source.remote.server.til

import com.dd2d.core.token_manager.TokenManager
import com.dd2d.data_source.remote.server._common.PagingResponseDto
import com.dd2d.data_source.remote.server._common.authorizationHeader
import com.dd2d.data_source.remote.server._common.bodyHandling
import com.dd2d.data_source.remote.server.til.dto.request.TILCreateRequestDto
import com.dd2d.data_source.remote.server.til.dto.request.TILUpdateRequestDto
import com.dd2d.data_source.remote.server.til.dto.response.TILListItemResponseDto
import com.dd2d.data_source.remote.server.til.dto.response.TILResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.parameters
import javax.inject.Inject
import javax.inject.Named

class TILApi @Inject constructor(
    @Named("server_client") private val client: HttpClient,
    private val tokenManager: TokenManager,
) {
    suspend fun getTILList(
        page: Int,
        size: Int,
    ): PagingResponseDto<TILListItemResponseDto> = client
        .get(urlString = "/v1/api/post/til/list") {
            authorizationHeader(token = tokenManager.getAccessToken())
            url {
                parameters.append("page", page.toString())
                parameters.append("size", size.toString())
            }
        }
        .bodyHandling()

    suspend fun getTILListBySearchKeyword(
        search: String,
        page: Int,
        size: Int,
    ): PagingResponseDto<TILListItemResponseDto> = client
        .get(urlString = "/v1/api/post/til/search") {
            authorizationHeader(token = tokenManager.getAccessToken())
            url {
                parameters.append("page", page.toString())
                parameters.append("size", size.toString())
                parameters.append("search", search)
            }
        }.bodyHandling()

    suspend fun getMyTILList(
        page: Int,
        size: Int,
    ): PagingResponseDto<TILListItemResponseDto> = client
        .get(urlString = "/v1/api/post/til/own") {
            authorizationHeader(token = tokenManager.getAccessToken())
            url {
                parameters.append("page", page.toString())
                parameters.append("size", size.toString())
            }
        }
        .bodyHandling()

    suspend fun getMyTILListBySearchKeyword(
        search: String,
        page: Int,
        size: Int,
    ): PagingResponseDto<TILListItemResponseDto> = client
        .get(urlString = "/v1/api/post/til/own/search") {
            authorizationHeader(token = tokenManager.getAccessToken())
            url {
                parameters.append("page", page.toString())
                parameters.append("size", size.toString())
                parameters.append("search", search)
            }
        }.bodyHandling()

    suspend fun getTIL(id: Int): TILResponseDto = client
        .get(urlString = "/v1/api/post/til/detail/$id") {
            authorizationHeader(token = tokenManager.getAccessToken())
        }
        .bodyHandling()

    suspend fun createTIL(body: TILCreateRequestDto): Int = client
        .post(urlString = "/v1/api/post/til") {
            authorizationHeader(token = tokenManager.getAccessToken())
            setBody(body)
        }
        .bodyHandling()

    suspend fun updateTIL(body: TILUpdateRequestDto): Int = client
        .put(urlString = "/v1/api/post/til") {
            authorizationHeader(token = tokenManager.getAccessToken())
            setBody(body)
        }
        .bodyHandling()

    suspend fun deleteTIL(id: Int): Int = client
        .delete(urlString = "/v1/api/post/til/$id") {
            authorizationHeader(token = tokenManager.getAccessToken())
        }
        .bodyHandling()
}
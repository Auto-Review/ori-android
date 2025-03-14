package com.dd2d.data_source.remote.server.til

import com.dd2d.core.token_manager.TokenManager
import com.dd2d.data_source.remote.server._common.PagingResponseDto
import com.dd2d.data_source.remote.server._common.authorizationHeader
import com.dd2d.data_source.remote.server._common.bodyHandling
import com.dd2d.data_source.remote.server.til.dto.request.TILCommentCreateRequestDto
import com.dd2d.data_source.remote.server.til.dto.request.TILCommentDeleteRequestDto
import com.dd2d.data_source.remote.server.til.dto.request.TILCommentUpdateRequestDto
import com.dd2d.data_source.remote.server.til.dto.response.TILCommentListItemResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import javax.inject.Inject
import javax.inject.Named

class TILCommentApi @Inject constructor(
    @Named("server_client") private val client: HttpClient,
    private val tokenManager: TokenManager,
) {
    suspend fun getTILCommentList(
        tilId: Int,
        page: Int,
        size: Int
    ): PagingResponseDto<TILCommentListItemResponseDto> = client
        .get(urlString = "/v1/api/til-post/${tilId}/USER/comments") {
            authorizationHeader(tokenManager.getAccessToken())
            url {
                parameters.append("page", "$page")
                parameters.append("size", "$size")
            }
        }
        .bodyHandling()

    suspend fun getTILCommentReplyList(
        tilId: Int,
        page: Int,
        size: Int,
    ): PagingResponseDto<TILCommentListItemResponseDto> = client
        .get(urlString = "/v1/api/til-post/${tilId}/USER/replies") {
            authorizationHeader(tokenManager.getAccessToken())
            url {
                parameters.append("page", "$page")
                parameters.append("size", "$size")
            }
        }
        .bodyHandling()

    suspend fun createTILComment(creator: TILCommentCreateRequestDto): Int = client
        .post(urlString = "/v1/api/til-post/comment") {
            authorizationHeader(tokenManager.getAccessToken())
            setBody(creator)
        }
        .bodyHandling()

    suspend fun updateTILComment(updater: TILCommentUpdateRequestDto): Int = client
        .put(urlString = "/v1/api/til-post/comment") {
            authorizationHeader(tokenManager.getAccessToken())
            setBody(updater)
        }
        .bodyHandling()

    suspend fun deleteTILComment(deleter: TILCommentDeleteRequestDto): Int = client
        .delete(urlString = "/v1/api/til-post/comment") {
            authorizationHeader(tokenManager.getAccessToken())
            setBody(deleter)
        }
        .bodyHandling()
}
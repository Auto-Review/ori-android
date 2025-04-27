package com.dd2d.data_source.remote.server.code_post

import com.dd2d.core.token_manager.TokenManager
import com.dd2d.data_source.remote.server._common.authorizationHeader
import com.dd2d.data_source.remote.server._common.bodyHandling
import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostReviewCreateRequestDto
import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostReviewDeleteRequestDto
import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostReviewUpdateRequestDto
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostReviewResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import javax.inject.Inject
import javax.inject.Named

class CodePostReviewApi @Inject constructor(
    @Named("server_client") private val client: HttpClient,
    private val tokenManager: TokenManager
) {
    suspend fun getCodePostReviewList(codePostId: Int): List<CodePostReviewResponseDto> = client
        .get(urlString = "/v1/api/review/${codePostId}/list") {
            authorizationHeader(tokenManager.getAccessToken())
        }
        .bodyHandling()

    suspend fun getCodePostReview(id: Int): CodePostReviewResponseDto = client
        .get(urlString = "/v1/api/review/detail/${id}") {
            authorizationHeader(tokenManager.getAccessToken())
        }
        .bodyHandling()

    suspend fun createCodePostReview(creator: CodePostReviewCreateRequestDto): String = client
        .post(urlString = "/v1/api/review") {
            authorizationHeader(tokenManager.getAccessToken())
            setBody(creator)
        }
        .bodyHandling()

    suspend fun updateCodePostReview(updater: CodePostReviewUpdateRequestDto): String = client
        .put(urlString = "/v1/api/review") {
            authorizationHeader(tokenManager.getAccessToken())
            setBody(updater)
        }
        .bodyHandling()

    suspend fun deleteCodePostReview(deleter: CodePostReviewDeleteRequestDto): String = client
        .delete(urlString = "/v1/api/review") {
            authorizationHeader(tokenManager.getAccessToken())
            setBody(deleter)
        }
        .bodyHandling()
}
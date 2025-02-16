package com.dd2d.data_source.remote.server.code_post

import com.dd2d.data_source.remote.server._common.PagingResponseDto
import com.dd2d.data_source.remote.server._common.bodyHandling
import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostCreateRequestDto
import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostUpdateRequestDto
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostListItemResponseDto
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.parameters
import javax.inject.Inject
import javax.inject.Named

class CodePostApi @Inject constructor(
    @Named("server_client") private val client: HttpClient,
) {
    suspend fun getCodePostList(
        page: Int,
        size: Int,
        sort: String? = null
    ): PagingResponseDto<CodePostListItemResponseDto> = client
        .get("/v1/api/post/code/list") {
            parameters {
                append("page", "$page")
                append("size", "$size")
                sort?.let { append("sort", sort) }
            }
        }
        .bodyHandling()

    suspend fun getCodePostListBySearchKeyword(
        search: String,
        page: Int,
        size: Int,
        sort: String? = null
    ): PagingResponseDto<CodePostListItemResponseDto> = client
        .get("/v1/api/post/code/search") {
            parameters {
                append("search", search)
                append("page", "$page")
                append("size", "$size")
                sort?.let { append("sort", sort) }
            }
        }
        .bodyHandling()

    suspend fun getMyCodePostList(
        page: Int,
        size: Int,
        sort: String? = null
    ): PagingResponseDto<CodePostListItemResponseDto> = client
        .get("/v1/api/post/code/own") {
            parameters {
                append("page", "$page")
                append("size", "$size")
                sort?.let { append("sort", sort) }
            }
        }
        .bodyHandling()

    suspend fun getMyCodePostListBySearchKeyword(
        search: String,
        page: Int,
        size: Int,
        sort: String? = null
    ): PagingResponseDto<CodePostListItemResponseDto> = client
        .get("/v1/api/post/code/own/search") {
            parameters {
                append("search", search)
                append("page", "$page")
                append("size", "$size")
                sort?.let { append("sort", sort) }
            }
        }
        .bodyHandling()

    suspend fun getCodePost(id: Int): CodePostResponseDto = client
        .get("/v1/api/post/code/detail/$id")
        .bodyHandling()

    suspend fun createCodePost(body: CodePostCreateRequestDto): Int = client
        .post("/v1/api/post/code") {
            setBody(body)
        }
        .bodyHandling<Int>()

    suspend fun updateCodePost(body: CodePostUpdateRequestDto): Int = client
        .put("/v1/api/post/code") {
            setBody(body)
        }
        .bodyHandling()

    suspend fun deleteCodePost(id: Int): Int = client
        .delete("/v1/api/post/code/$id")
        .bodyHandling()
}
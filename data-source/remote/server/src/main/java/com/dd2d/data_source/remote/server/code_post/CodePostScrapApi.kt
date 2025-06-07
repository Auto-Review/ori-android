package com.dd2d.data_source.remote.server.code_post

import com.dd2d.core.token_manager.TokenManager
import com.dd2d.data_source.remote.server._common.PagingResponseDto
import com.dd2d.data_source.remote.server._common.authorizationHeader
import com.dd2d.data_source.remote.server._common.bodyHandling
import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostToggleScrapRequestDto
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostScrapListResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import javax.inject.Inject
import javax.inject.Named

class CodePostScrapApi @Inject constructor(
  @Named("server_client") private val client: HttpClient,
  private val tokenManager: TokenManager
) {
  suspend fun toggleScrap(
    body: CodePostToggleScrapRequestDto
  ): Unit = client
    .put(urlString = "/v1/api/post/code/bookmark") {
      authorizationHeader(tokenManager.getAccessToken())
      setBody(body)
    }.bodyHandling()

  suspend fun getMyScrap(
    page: Int,
    size: Int,
  ): PagingResponseDto<CodePostScrapListResponseDto> = client
    .get(urlString = "/v1/api/post/code/bookmark/list") {
      authorizationHeader(tokenManager.getAccessToken())
      url {
        parameters.append("page", "$page")
        parameters.append("size", "$size")
      }
    }.bodyHandling()
}
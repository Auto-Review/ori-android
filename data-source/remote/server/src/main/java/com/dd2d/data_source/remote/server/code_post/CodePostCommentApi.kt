package com.dd2d.data_source.remote.server.code_post

import com.dd2d.core.token_manager.TokenManager
import com.dd2d.data_source.remote.server._common.CommentPagingResponseDto
import com.dd2d.data_source.remote.server._common.authorizationHeader
import com.dd2d.data_source.remote.server._common.bodyHandling
import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostCommentCreateRequestDto
import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostCommentDeleteRequestDto
import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostCommentUpdateRequestDto
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostCommentListItemResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import javax.inject.Inject
import javax.inject.Named

class CodePostCommentApi @Inject constructor(
  @Named("server_client") private val client: HttpClient,
  private val tokenManager: TokenManager,
) {
  suspend fun getCodePostCommentList(
    codePostId: Int,
    page: Int,
    size: Int,
  ): CommentPagingResponseDto<CodePostCommentListItemResponseDto> = client
    .get(urlString = "/v1/api/code-post/${codePostId}/USER/comments") {
      authorizationHeader(tokenManager.getAccessToken())
      url {
        parameters.append("page", "$page")
        parameters.append("size", "$size")
      }
    }
    .bodyHandling()

  suspend fun getCodePostCommentReplyList(
    codePostId: Int,
    parentCommentId: Int,
    page: Int,
    size: Int,
  ): CommentPagingResponseDto<CodePostCommentListItemResponseDto> = client
    .get(urlString = "/v1/api/code-post/${codePostId}/USER/replies") {
      authorizationHeader(tokenManager.getAccessToken())
      url {
        parameters.append("parentId", "$parentCommentId")
        parameters.append("page", "$page")
        parameters.append("size", "$size")
      }
    }
    .bodyHandling()

  suspend fun createCodePostComment(creator: CodePostCommentCreateRequestDto): Int = client
    .post(urlString = "/v1/api/code-post/comment") {
      authorizationHeader(tokenManager.getAccessToken())
      setBody(creator)
    }
    .bodyHandling()

  suspend fun updateCodePostComment(updater: CodePostCommentUpdateRequestDto): Int = client
    .put(urlString = "/v1/api/code-post/comment") {
      authorizationHeader(tokenManager.getAccessToken())
      setBody(updater)
    }
    .bodyHandling()

  suspend fun deleteCodePostComment(deleter: CodePostCommentDeleteRequestDto): Int = client
    .delete(urlString = "/v1/api/code-post/comment") {
      authorizationHeader(tokenManager.getAccessToken())
      setBody(deleter)
    }
    .bodyHandling()
}
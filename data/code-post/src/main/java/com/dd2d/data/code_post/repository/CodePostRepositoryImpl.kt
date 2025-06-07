package com.dd2d.data.code_post.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.data.code_post.mapper.toCodePost
import com.dd2d.data.code_post.mapper.toCodePostListItem
import com.dd2d.data.code_post.mapper.toCodePostUpdateRequestDto
import com.dd2d.data.code_post.mapper.toCorePostCreateRequestDto
import com.dd2d.data_source.remote.server._common.toPagination
import com.dd2d.data_source.remote.server.code_post.CodePostApi
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostListItemResponseDto
import com.dd2d.domain.code_post.model.post.CodePost
import com.dd2d.domain.code_post.model.post.CodePostCreator
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.domain.code_post.model.post.CodePostListOptions
import com.dd2d.domain.code_post.model.post.CodePostUpdater
import com.dd2d.domain.code_post.repository.CodePostRepository
import javax.inject.Inject

class CodePostRepositoryImpl @Inject constructor(
  private val codePostApi: CodePostApi,
) : CodePostRepository {
  override suspend fun getCodePostList(options: CodePostListOptions): Result<Pagination<CodePostListItem>> {
    return codePostApi
      .runCatching {
        if(options.search.isEmpty()) {
          getCodePostList(
            page = options.page,
            size = options.take,
            sortBy = options.sort.sortByValue,
            direction = options.sort.directionValue,
            language = options.language?.value,
          )
        }
        else {
          getCodePostListBySearchKeyword(
            keyword = options.search,
            page = options.page,
            size = options.take,
          )
        }
      }
      .mapCatching { response ->
        response.toPagination(
          requestPage = options.page,
          mapper = CodePostListItemResponseDto::toCodePostListItem
        )
      }
  }

  override suspend fun getMyCodePostList(options: CodePostListOptions): Result<Pagination<CodePostListItem>> {
    return codePostApi
      .runCatching {
        getMyCodePostList(
          page = options.page,
          size = options.take,
          sortBy = options.sort.sortByValue,
          direction = options.sort.directionValue,
          language = options.language?.value,

          )
      }
      .mapCatching { response ->
        response.toPagination(
          requestPage = options.page,
          mapper = CodePostListItemResponseDto::toCodePostListItem
        )
      }
  }

  override suspend fun getCodePost(id: Int): Result<CodePost> {
    return codePostApi.runCatching {
      getCodePost(id).toCodePost()
    }
  }

  override suspend fun createCodePost(create: CodePostCreator): Result<Int> {
    return codePostApi.runCatching {
      createCodePost(create.toCorePostCreateRequestDto())
    }
  }

  override suspend fun updateCodePost(update: CodePostUpdater): Result<Unit> {
    return codePostApi.runCatching {
      updateCodePost(update.toCodePostUpdateRequestDto())
    }
  }

  override suspend fun deleteCodePost(id: Int): Result<Unit> {
    return codePostApi.runCatching {
      deleteCodePost(id)
    }
  }
}
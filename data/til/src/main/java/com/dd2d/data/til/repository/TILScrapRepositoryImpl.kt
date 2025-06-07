package com.dd2d.data.til.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.data.til.mapper.toTILListItem
import com.dd2d.data_source.remote.server._common.toPagination
import com.dd2d.data_source.remote.server.til.TILScrapApi
import com.dd2d.data_source.remote.server.til.dto.request.TILScrapCreateRequestDto
import com.dd2d.data_source.remote.server.til.dto.response.TILListItemResponseDto
import com.dd2d.domain.til.model.TILListItem
import com.dd2d.domain.til.model.TILScrapListOption
import com.dd2d.domain.til.repository.TILScrapRepository
import javax.inject.Inject

class TILScrapRepositoryImpl @Inject constructor(
  private val tilScrapApi: TILScrapApi
) : TILScrapRepository {
  override suspend fun getMyScrapList(option: TILScrapListOption): Result<Pagination<TILListItem>> {
    return tilScrapApi
      .runCatching {
        getMyScrapList(
          page = option.page,
          size = option.take,
        )
      }
      .mapCatching { response ->
        response.toPagination(
          requestPage = option.page,
          mapper = TILListItemResponseDto::toTILListItem
        )
      }
  }

  override suspend fun scrap(tilId: Int): Result<Unit> {
    return tilScrapApi
      .runCatching {
        createScrap(body = TILScrapCreateRequestDto(postId = tilId))
      }
  }
}
package com.dd2d.data.til.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.data.til.mapper.toTIL
import com.dd2d.data.til.mapper.toTILCreateRequestDto
import com.dd2d.data.til.mapper.toTILListItem
import com.dd2d.data.til.mapper.toTILUpdateRequestDto
import com.dd2d.data_source.remote.server._common.toPagination
import com.dd2d.data_source.remote.server.til.TILApi
import com.dd2d.data_source.remote.server.til.dto.response.TILListItemResponseDto
import com.dd2d.domain.til.model.TIL
import com.dd2d.domain.til.model.TILCreator
import com.dd2d.domain.til.model.TILListItem
import com.dd2d.domain.til.model.TILListOptions
import com.dd2d.domain.til.model.TILUpdater
import com.dd2d.domain.til.repository.TILRepository
import javax.inject.Inject

class TILRepositoryImpl @Inject constructor(
    private val tilApi: TILApi
): TILRepository {
    override suspend fun getTILList(options: TILListOptions): Result<Pagination<TILListItem>> {
        return tilApi
            .runCatching {
                if(options.search.isBlank()) tilApi.getTILList(page = options.page, size = options.take)
                else tilApi.getTILListBySearchKeyword(search = options.search, page = options.page, size = options.take)
            }
            .mapCatching { response ->
                response.toPagination(
                    requestPage = options.page,
                    mapper = TILListItemResponseDto::toTILListItem,
                )
            }
    }

    override suspend fun getMyTILList(options: TILListOptions): Result<Pagination<TILListItem>> {
        return tilApi
            .runCatching {
                if(options.search.isBlank()) tilApi.getMyTILList(page = options.page, size = options.take)
                else tilApi.getMyTILListBySearchKeyword(search = options.search, page = options.page, size = options.take)
            }
            .mapCatching { response ->
                response.toPagination(
                    requestPage = options.page,
                    mapper = TILListItemResponseDto::toTILListItem
                )
            }
    }

    override suspend fun getTIL(id: Int): Result<TIL> {
        return tilApi
            .runCatching {
                getTIL(id)
            }
            .mapCatching { response ->
                response.toTIL()
            }
    }

    override suspend fun createTIL(create: TILCreator): Result<Int> {
        return tilApi
            .runCatching {
                createTIL(create.toTILCreateRequestDto())
            }
    }

    override suspend fun updateTIL(update: TILUpdater): Result<Int> {
        return tilApi
            .runCatching {
                updateTIL(update.toTILUpdateRequestDto())
            }
    }

    override suspend fun deleteTIL(id: Int): Result<Int> {
        return tilApi
            .runCatching {
                deleteTIL(id)
            }
    }
}
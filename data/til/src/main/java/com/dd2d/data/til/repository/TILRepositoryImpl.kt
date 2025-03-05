package com.dd2d.data.til.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.asDataState
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TILRepositoryImpl @Inject constructor(
    private val tilApi: TILApi
): TILRepository {
    override fun getTILList(options: TILListOptions): Flow<DataState<Pagination<TILListItem>>> = flow {
        val response = with(options) {
            if(search.isBlank()) tilApi.getTILList(page = page, size = take)
            else tilApi.getTILListBySearchKeyword(search = search, page = page, size = take)
        }
        emit(
            response.toPagination(
                requestPage = options.page,
                mapper = TILListItemResponseDto::toTILListItem
            )
        )
    }.asDataState()

    override fun getMyTILList(options: TILListOptions): Flow<DataState<Pagination<TILListItem>>> = flow {
        val response = with(options) {
            if(search.isBlank()) tilApi.getMyTILList(page = page, size = take)
            else tilApi.getMyTILListBySearchKeyword(search = search, page = page, size = take)
        }
        emit(
            response.toPagination(
                requestPage = options.page,
                mapper = TILListItemResponseDto::toTILListItem
            )
        )
    }.asDataState()

    override fun getTIL(id: Int): Flow<DataState<TIL>> = flow {
        val response = tilApi.getTIL(id)
        emit(response.toTIL())
    }.asDataState()

    override fun createTIL(create: TILCreator): Flow<DataState<Int>> = flow {
        val response = tilApi.createTIL(create.toTILCreateRequestDto())
        emit(response)
    }.asDataState()

    override fun updateTIL(update: TILUpdater): Flow<DataState<Int>> = flow {
        val response = tilApi.updateTIL(update.toTILUpdateRequestDto())
        emit(response)
    }.asDataState()

    override fun deleteTIL(id: Int): Flow<DataState<Int>> = flow {
        val response = tilApi.deleteTIL(id)
        emit(response)
    }.asDataState()
}
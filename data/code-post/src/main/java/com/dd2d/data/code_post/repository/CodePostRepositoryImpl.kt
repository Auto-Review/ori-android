package com.dd2d.data.code_post.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.asDataState
import com.dd2d.data.code_post.mapper.toCodePost
import com.dd2d.data.code_post.mapper.toCodePostListItem
import com.dd2d.data.code_post.mapper.toCodePostUpdateRequestDto
import com.dd2d.data.code_post.mapper.toCorePostCreateRequestDto
import com.dd2d.data_source.remote.server.code_post.CodePostApi
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostListItemResponseDto
import com.dd2d.domain.code_post.model.CodePost
import com.dd2d.domain.code_post.model.CodePostCreator
import com.dd2d.domain.code_post.model.CodePostListItem
import com.dd2d.domain.code_post.model.CodePostListOptions
import com.dd2d.domain.code_post.model.CodePostUpdater
import com.dd2d.domain.code_post.repository.CodePostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CodePostRepositoryImpl @Inject constructor(
    private val codePostApi: CodePostApi,
): CodePostRepository {
    override fun getCodePostList(options: CodePostListOptions): Flow<DataState<Pagination<CodePostListItem>>> = flow {
        val response = with(options) {
            if(search.isBlank()) codePostApi.getCodePostList(page = page, size = take, sort = sort)
            else codePostApi.getCodePostListBySearchKeyword(search = search, page = page, size = take, sort = sort)
        }
        emit(
            Pagination(
                list = response.dtoList.map(CodePostListItemResponseDto::toCodePostListItem),
                currentPage = options.page,
                totalPage = response.totalPage,
                totalItemCount = -1,
            )
        )
    }.asDataState()

    override fun getMyCodePostList(options: CodePostListOptions): Flow<DataState<Pagination<CodePostListItem>>> = flow {
        val response = with(options){
            if(search.isBlank()) codePostApi.getMyCodePostList(page = page, size = take, sort = sort)
            else codePostApi.getMyCodePostListBySearchKeyword(search = search, page = page, size = take, sort = sort)
        }

        emit(
            Pagination(
                list = response.dtoList.map(CodePostListItemResponseDto::toCodePostListItem),
                currentPage = options.page,
                totalPage = response.totalPage,
                totalItemCount = -1,
            )
        )
    }.asDataState()

    override fun getCodePost(id: Int): Flow<DataState<CodePost>> = flow {
        val response = codePostApi.getCodePost(id)
        emit(response.toCodePost())
    }.asDataState()

    override fun createCodePost(create: CodePostCreator): Flow<DataState<Int>> = flow {
        val response = codePostApi.createCodePost(create.toCorePostCreateRequestDto())
        emit(response)
    }.asDataState()

    override fun updateCodePost(update: CodePostUpdater): Flow<DataState<Boolean>> = flow {
        val response = codePostApi.updateCodePost(update.toCodePostUpdateRequestDto())
        emit(true)
    }.asDataState()

    override fun deleteCodePost(id: Int): Flow<DataState<Boolean>> = flow {
        val response = codePostApi.deleteCodePost(id)
        emit(true)
    }.asDataState()
}
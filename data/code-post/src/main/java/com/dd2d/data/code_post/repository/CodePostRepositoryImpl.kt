package com.dd2d.data.code_post.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.asDataState
import com.dd2d.domain.code_post.model.CodePost
import com.dd2d.domain.code_post.model.CodePostCreator
import com.dd2d.domain.code_post.model.CodePostListItem
import com.dd2d.domain.code_post.model.CodePostListOptions
import com.dd2d.domain.code_post.model.CodePostUpdater
import com.dd2d.domain.code_post.repository.CodePostRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CodePostRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
): CodePostRepository {
    override fun getCodePostList(options: CodePostListOptions): Flow<DataState<Pagination<CodePostListItem>>> = flow {
        delay(500)
        val list = List(options.take) {
            CodePostListItem.dummy.copy(
                id = options.page *options.take +it
            )
        }

        emit(
            Pagination(
                list = list,
                currentPage = options.page,
                totalPage = Int.MAX_VALUE,
                totalItemCount = Int.MAX_VALUE,
            )

        )
    }.asDataState()

    override fun getCodePost(id: Int): Flow<DataState<CodePost>> = flow {
        delay(500)
        emit(CodePost.dummy)
    }.asDataState()

    override fun getMyCodePostList(options: CodePostListOptions): Flow<DataState<Pagination<CodePostListItem>>> = flow {
        delay(500)
        val list = List(options.take) {
            CodePostListItem.dummy.copy(
                id = options.page *options.take +it
            )
        }

        emit(
            Pagination(
                list = list,
                currentPage = options.page,
                totalPage = Int.MAX_VALUE,
                totalItemCount = Int.MAX_VALUE,
            )

        )
    }.asDataState()

    override fun createCodePost(create: CodePostCreator): Flow<DataState<Int>> = flow {
        delay(500)
        emit(1)
    }.asDataState()

    override fun updateCodePost(update: CodePostUpdater): Flow<DataState<Boolean>> = flow {
        delay(500)
        emit(true)
    }.asDataState()

    override fun deleteCodePost(id: Int): Flow<DataState<Boolean>> = flow {
        delay(500)
        emit(true)
    }.asDataState()
}
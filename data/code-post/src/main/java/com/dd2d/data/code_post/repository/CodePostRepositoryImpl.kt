package com.dd2d.data.code_post.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.asDataState
import com.dd2d.core.core.util.format
import com.dd2d.domain.code_post.model.CodePost
import com.dd2d.domain.code_post.model.CodePostAuthor
import com.dd2d.domain.code_post.model.CodePostCreator
import com.dd2d.domain.code_post.model.CodePostListItem
import com.dd2d.domain.code_post.model.CodePostListOptions
import com.dd2d.domain.code_post.model.CodePostUpdater
import com.dd2d.domain.code_post.repository.CodePostRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Named
import kotlin.math.min

object TestServer {
    val list = MutableList(5) {
        CodePost.dummy.copy(id = it, title = "${it}번째 게시물")
    }

    suspend fun add(request: CodePostCreator): Int {
        delay(500)
        list.add(
            CodePost(
                id = list.size,
                author = CodePostAuthor(id = 1, nickname = "테스터", profileImageUrl = null),
                title = request.title,
                code = request.code,
                description = request.description,
                reviewDate = request.reviewDate,
                level = request.level,
                createdAt = LocalDateTime.now().format("yyyy-MM-dd"),
            )
        )
        return list.lastIndex
    }

    suspend fun getList(options: CodePostListOptions): List<CodePostListItem> {
        delay(500)
        val from = (options.page - 1) * options.take
        val to = from + options.take
        return list.reversed()
            .subList(from, min(list.size, to))
            .let { list ->
                if(options.search.isNotBlank()) {
                    list.filter { it.title.contains(options.search) }
                }
                else {
                    list
                }
            }
            .map {
                CodePostListItem(
                    id = it.id,
                    author = it.author,
                    title = it.title,
                    level = it.level,
                    description = it.description,
                    createdAt = it.createdAt,
                )
            }
    }

    suspend fun get(id: Int): CodePost? {
        delay(500)
        return list.find { it.id == id }
    }

    suspend fun delete(id: Int): Boolean {
        delay(500)
        return list.removeIf { it.id == id }
    }
}

class CodePostRepositoryImpl @Inject constructor(
    @Named("server_client") private val httpClient: HttpClient
): CodePostRepository {
    override fun getCodePostList(options: CodePostListOptions): Flow<DataState<Pagination<CodePostListItem>>> = flow {
        val list = TestServer.getList(options)

        emit(
            Pagination(
                list = list,
                currentPage = options.page,
                totalPage = TestServer.list.size / options.take + 1,
                totalItemCount = TestServer.list.size,
            )
        )
    }.asDataState()

    override fun getCodePost(id: Int): Flow<DataState<CodePost>> = flow {
        TestServer.get(id)
            ?.let { response ->
                emit(response)
            }
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
        val response = TestServer.add(create)
        emit(response)
    }.asDataState()

    override fun updateCodePost(update: CodePostUpdater): Flow<DataState<Boolean>> = flow {
        delay(500)
        emit(true)
    }.asDataState()

    override fun deleteCodePost(id: Int): Flow<DataState<Boolean>> = flow {
        val response = TestServer.delete(id)
        emit(response)
    }.asDataState()
}
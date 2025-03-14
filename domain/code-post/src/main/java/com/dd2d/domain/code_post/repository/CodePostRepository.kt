package com.dd2d.domain.code_post.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.core.core.state.DataState
import com.dd2d.domain.code_post.model.post.CodePost
import com.dd2d.domain.code_post.model.post.CodePostCreator
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.domain.code_post.model.post.CodePostListOptions
import com.dd2d.domain.code_post.model.post.CodePostUpdater
import kotlinx.coroutines.flow.Flow

interface CodePostRepository {
    fun getCodePostList(options: CodePostListOptions): Flow<DataState<Pagination<CodePostListItem>>>
    fun getMyCodePostList(options: CodePostListOptions): Flow<DataState<Pagination<CodePostListItem>>>
    fun getCodePost(id: Int): Flow<DataState<CodePost>>
    fun createCodePost(create: CodePostCreator): Flow<DataState<Int>>
    fun updateCodePost(update: CodePostUpdater): Flow<DataState<Boolean>>
    fun deleteCodePost(id: Int): Flow<DataState<Boolean>>
}
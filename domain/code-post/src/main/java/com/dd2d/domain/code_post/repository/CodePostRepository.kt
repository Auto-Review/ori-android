package com.dd2d.domain.code_post.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.domain.code_post.model.post.CodePost
import com.dd2d.domain.code_post.model.post.CodePostCreator
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.domain.code_post.model.post.CodePostListOptions
import com.dd2d.domain.code_post.model.post.CodePostUpdater

interface CodePostRepository {
  suspend fun getCodePostList(options: CodePostListOptions): Result<Pagination<CodePostListItem>>
  suspend fun getMyCodePostList(options: CodePostListOptions): Result<Pagination<CodePostListItem>>
  suspend fun getCodePost(id: Int): Result<CodePost>
  suspend fun createCodePost(create: CodePostCreator): Result<Int>
  suspend fun updateCodePost(update: CodePostUpdater): Result<Unit>
  suspend fun deleteCodePost(id: Int): Result<Unit>
}
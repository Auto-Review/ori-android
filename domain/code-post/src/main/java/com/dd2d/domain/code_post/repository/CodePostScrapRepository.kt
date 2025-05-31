package com.dd2d.domain.code_post.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.domain.code_post.model.scrap.CodePostScrapListOption

interface CodePostScrapRepository {
    suspend fun toggleScrap(codePostId: Int): Result<Unit>
    suspend fun getMyCodePostScrapList(option: CodePostScrapListOption): Result<Pagination<CodePostListItem>>
}
package com.dd2d.data.code_post.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.data.code_post.mapper.toCodePostListItem
import com.dd2d.data_source.remote.server._common.toPagination
import com.dd2d.data_source.remote.server.code_post.CodePostScrapApi
import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostToggleScrapRequestDto
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostListItemResponseDto
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.domain.code_post.model.scrap.CodePostScrapListOption
import com.dd2d.domain.code_post.repository.CodePostScrapRepository
import javax.inject.Inject

class CodePostScrapRepositoryImpl @Inject constructor(
    private val codePostScrapApi: CodePostScrapApi
): CodePostScrapRepository {
    override suspend fun toggleScrap(codePostId: Int): Result<Unit> {
        return codePostScrapApi
            .runCatching {
                toggleScrap(body = CodePostToggleScrapRequestDto(codePostId = codePostId))
            }
    }

    override suspend fun getMyCodePostScrapList(option: CodePostScrapListOption): Result<Pagination<CodePostListItem>> {
        return codePostScrapApi
            .runCatching {
                getMyScrap(page = option.page, size = option.page)
            }
            .mapCatching { response ->
                response.toPagination(
                    requestPage = option.page,
                    mapper = CodePostListItemResponseDto::toCodePostListItem
                )
            }
    }
}
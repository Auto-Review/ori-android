package com.dd2d.domain.til.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.domain.til.model.TILListItem
import com.dd2d.domain.til.model.TILScrapListOption

interface TILScrapRepository {
    suspend fun getMyScrapList(option: TILScrapListOption): Result<Pagination<TILListItem>>
    suspend fun scrap(tilId: Int): Result<Unit>
}
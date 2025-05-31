package com.dd2d.domain.til.model

import com.dd2d.core.core.model.Pageable

data class TILScrapListOption(
    override val page: Int = 0,
    override val take: Int = 15,
): Pageable<TILScrapListOption> {
    override fun pageAt(page: Int): TILScrapListOption = copy(page = page)
}

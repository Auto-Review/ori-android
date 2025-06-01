package com.dd2d.domain.code_post.model.scrap

import com.dd2d.core.core.model.Pageable

data class CodePostScrapListOption(
    override val page: Int = 0,
    override val take: Int = 15,
): Pageable<CodePostScrapListOption> {
    override fun pageAt(page: Int): CodePostScrapListOption = copy(page = page)
}

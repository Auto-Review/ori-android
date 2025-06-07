package com.dd2d.domain.til.model

import com.dd2d.core.core.model.Pageable

data class TILListOptions(
  override val page: Int = 0,
  override val take: Int = 20,
  val search: String = "",
) : Pageable<TILListOptions> {
  override fun pageAt(page: Int): TILListOptions = copy(page = page)
}

package com.dd2d.domain.til.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.domain.til.model.TIL
import com.dd2d.domain.til.model.TILCreator
import com.dd2d.domain.til.model.TILListItem
import com.dd2d.domain.til.model.TILListOptions
import com.dd2d.domain.til.model.TILUpdater

interface TILRepository {
  suspend fun getTILList(options: TILListOptions): Result<Pagination<TILListItem>>
  suspend fun getMyTILList(options: TILListOptions): Result<Pagination<TILListItem>>
  suspend fun getTIL(id: Int): Result<TIL>
  suspend fun createTIL(create: TILCreator): Result<Int>
  suspend fun updateTIL(update: TILUpdater): Result<Int>
  suspend fun deleteTIL(id: Int): Result<Int>
}
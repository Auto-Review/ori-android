package com.dd2d.domain.til.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.core.core.state.DataState
import com.dd2d.domain.til.model.TIL
import com.dd2d.domain.til.model.TILCreator
import com.dd2d.domain.til.model.TILListItem
import com.dd2d.domain.til.model.TILListOptions
import com.dd2d.domain.til.model.TILUpdater
import kotlinx.coroutines.flow.Flow

interface TILRepository {
    fun getTILList(options: TILListOptions): Flow<DataState<Pagination<TILListItem>>>
    fun getMyTILList(options: TILListOptions): Flow<DataState<Pagination<TILListItem>>>
    fun getTIL(id: Int): Flow<DataState<TIL>>
    fun createTIL(create: TILCreator): Flow<DataState<Int>>
    fun updateTIL(update: TILUpdater): Flow<DataState<Boolean>>
    fun deleteTIL(id: Int): Flow<DataState<Boolean>>
}
package com.dd2d.core.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import com.dd2d.core.core.model.Pageable
import com.dd2d.core.core.model.Pagination

class PagerManager <Option: Pageable<Option>, ListItem: Any>(
    initialOption: Option,
    getList: suspend (option: Option) -> Result<Pagination<ListItem>>,
    config: PagingConfig = PagingConfig(pageSize = initialOption.take),
) {
    var option by mutableStateOf(initialOption)

    val pagingData = Pager(
        config = config,
        initialKey = option.page,
        pagingSourceFactory = {
            PagingSource { page ->
                option = option.pageAt(page)
                getList(option)
            }
        }
    )
    val flow = pagingData.flow
}

class PagingSource <ListItem: Any>(
    private val getList: suspend (page: Int) -> Result<Pagination<ListItem>>,
): androidx.paging.PagingSource<Int, ListItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListItem> {
        val page = params.key?: 0
        return getList(page).fold(
            onSuccess = { pagination ->
                LoadResult.Page(
                    data = pagination.list,
                    prevKey = if(page > 0) page-1 else null,
                    nextKey = if(page+1 < pagination.totalPage) page+1 else null,
                )
            },
            onFailure = { throwable ->
                LoadResult.Error(throwable = throwable)
            },
        )
    }

    override fun getRefreshKey(state: PagingState<Int, ListItem>): Int {
        return ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2).coerceAtLeast(0)
    }
}
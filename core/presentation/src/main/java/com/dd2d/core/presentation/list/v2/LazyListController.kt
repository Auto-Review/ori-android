package com.dd2d.core.presentation.list.v2

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd2d.core.core.model.Pageable
import com.dd2d.core.core.model.Pagination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LazyListController<ListOption: Pageable<ListOption>, ListItem>(
    option: ListOption,
    private val scope: CoroutineScope,
    private val getList: suspend (option: ListOption) -> Result<Pagination<ListItem>>
) {
    var state by mutableStateOf<LazyListControllerState>(LazyListControllerState.Idle)
        private set

    var option by mutableStateOf(option); private set
    fun updateOption(block: (origin: ListOption) -> ListOption) {
        scope.launch {
            option = block(option).pageAt(0)
            getList(loadType = LoadType.Refresh, option = option)
        }
    }

    private var cachedLastPage = 0

    val list = mutableStateListOf<ListItem>()

    var totalPage by mutableIntStateOf(0); private set
    var canLoadNext by mutableStateOf(false); private set

    val loadNextPageTriggerIndex by derivedStateOf {
        if(canLoadNext) list.lastIndex - option.take * 2 else null
    }

    fun nextPage() {
        if(!canLoadNext || state is LazyListControllerState.Loading) return
        scope.launch {
            state = LazyListControllerState.Loading(LoadType.Next)
            option = option.pageAt(page = cachedLastPage + 1)
            getList(loadType = LoadType.Next, option = option)
        }
    }

    fun refresh() {
        scope.launch {
            state = LazyListControllerState.Loading(LoadType.Refresh)
            option = option.pageAt(page = 0)
            getList(loadType = LoadType.Refresh, option = option)
        }
    }

    private suspend fun getList(loadType: LoadType, option: ListOption) {
        state = getList(option).fold(
            onFailure = { LazyListControllerState.Error(it) },
            onSuccess = { data ->
                totalPage = data.totalPage
                canLoadNext = option.page + 1 < totalPage
                when(loadType) {
                    LoadType.Refresh -> {
                        list.clear()
                        cachedLastPage = 0
                        list.addAll(data.list)
                    }
                    LoadType.Next -> {
                        list.addAll(data.list)
                        cachedLastPage = option.page
                    }
                }
                LazyListControllerState.Idle
            }
        )
    }
    init { refresh() }
}

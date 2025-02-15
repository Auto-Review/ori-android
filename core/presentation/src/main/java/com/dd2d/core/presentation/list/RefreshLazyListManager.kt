package com.dd2d.core.presentation.list

import androidx.compose.runtime.mutableStateListOf
import com.dd2d.core.core.model.Pagination
import com.dd2d.core.core.state.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class RefreshLazyListManager <ListOptions, ListItemModel>(
    initialState: RefreshLazyListState = RefreshLazyListState.Success,
    initialListOption: ListOptions,
    private val scope: CoroutineScope,
    private val flow: (options: ListOptions) -> Flow<DataState<Pagination<ListItemModel>>>,
    private val lazyInit: Boolean = false,
) {
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    var options = initialListOption
        private set

    val list = mutableStateListOf<ListItemModel>()
    val isLastPage = MutableStateFlow(false)
    val totalItem = MutableStateFlow(0)
    val totalPage = MutableStateFlow(0)

    private fun getList(
        options: ListOptions,
        isRefresh: Boolean = false,
    ) {
        flow(options)
            .onEach { state ->
                when(state) {
                    is DataState.Loading -> {
                        _state.update {
                            if(isRefresh) RefreshLazyListState.Refreshing
                            else RefreshLazyListState.Loading
                        }
                    }
                    is DataState.Error -> _state.update { RefreshLazyListState.Error(state.exception) }
                    is DataState.Success -> {
                        if(isRefresh) { list.clear() }

                        with(state.data) {
                            this@RefreshLazyListManager.list.addAll(this.list)
                            this@RefreshLazyListManager.isLastPage.value = this.currentPage == this.totalPage
                            this@RefreshLazyListManager.totalPage.value = this.totalPage
                            this@RefreshLazyListManager.totalItem.value = this.totalItemCount
                        }

                        this@RefreshLazyListManager.options = options
                        _state.update { RefreshLazyListState.Success }
                    }
                }
            }
            .launchIn(scope)
    }

    fun refresh(options: ListOptions) {
        getList(options = options, isRefresh = true)
    }

    fun loadMore(options: ListOptions) {
        if(isLastPage.value) return
        getList(options = options, isRefresh = false)
    }

    init {
        if(!lazyInit) {
            getList(options = options, isRefresh = false)
        }
    }
}
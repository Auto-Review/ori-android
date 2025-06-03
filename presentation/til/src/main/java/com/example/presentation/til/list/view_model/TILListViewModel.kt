package com.example.presentation.til.list.view_model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.domain.til.model.TILListOptions
import com.dd2d.domain.til.repository.TILRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TILListViewModel @Inject constructor(
    private val tilRepository: TILRepository,
) : ViewModel() {
    val tilListController = LazyListController(
        option = TILListOptions(),
        scope = viewModelScope,
        getList = tilRepository::getTILList,
    )

//    val listManager = RefreshLazyListManager(
//        initialListOption = TILListOptions(),
//        scope = viewModelScope,
//        flow = tilRepository::getTILList,
//        lazyInit = true,
//    )
//
//    fun nextPage() = with(listManager) { loadMore(options = options.copy(page = options.page +1)) }
//    fun refresh() = with(listManager) { refresh(options = TILListOptions()) }
}
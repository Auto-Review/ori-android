package com.dd2d.presentation.code_post.content.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.presentation.list.RefreshLazyListManager
import com.dd2d.domain.code_post.model.post.CodePostListOptions
import com.dd2d.domain.code_post.repository.CodePostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CodePostListViewModel @Inject constructor(
    private val codePostRepository: CodePostRepository
): ViewModel() {
    val codePostListManager = RefreshLazyListManager(
        initialListOption = CodePostListOptions(),
        scope = viewModelScope,
        flow = codePostRepository::getCodePostList,
        lazyInit = true
    )
    fun onNextPage() = with(codePostListManager) { loadMore(options.copy(page = options.page + 1)) }
    fun onRefresh() = with(codePostListManager) { refresh(CodePostListOptions()) }
    fun search(searchText: String) = with(codePostListManager) { refresh(CodePostListOptions(search = searchText)) }
}

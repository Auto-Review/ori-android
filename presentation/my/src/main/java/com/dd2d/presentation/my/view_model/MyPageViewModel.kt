package com.dd2d.presentation.my.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.list.RefreshLazyListManager
import com.dd2d.domain.code_post.model.post.CodePostListOptions
import com.dd2d.domain.code_post.repository.CodePostRepository
import com.dd2d.domain.til.model.TILListOptions
import com.dd2d.domain.til.repository.TILRepository
import com.dd2d.domain.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class MyPageViewModel @Inject constructor(
    userRepository: UserRepository,
    private val codePostRepository: CodePostRepository,
    private val tilRepository: TILRepository,
): ViewModel() {
    val userState = userRepository.me()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = DataState.Loading
        )

    val codePostListManager = RefreshLazyListManager(
        initialListOption = CodePostListOptions(),
        scope = viewModelScope,
        flow = codePostRepository::getMyCodePostList,
        lazyInit = true
    )
    fun nextCodePostPage() = with(codePostListManager) { loadMore(options = options.copy(page = options.page + 1)) }
    fun refreshCodePostList() = with(codePostListManager) { refresh(options = CodePostListOptions()) }

    val tilListManager = RefreshLazyListManager(
        initialListOption = TILListOptions(),
        scope = viewModelScope,
        flow = tilRepository::getMyTILList,
        lazyInit = true
    )
    fun nextTILPage() = with(tilListManager) { loadMore(options = options.copy(page = options.page + 1)) }
    fun refreshTILList() = with(tilListManager) { refresh(options = TILListOptions()) }
}

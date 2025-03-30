package com.dd2d.presentation.code_post.detail.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.onEachState
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.domain.code_post.repository.CodePostRepository
import com.dd2d.presentation.code_post.detail._navigation.CodePostScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class CodePostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val codePostRepository: CodePostRepository,
): ViewModel(), UIStateManager {
    override val uiState = MutableStateFlow<UIState>(UIState.Idle)

    private val route = savedStateHandle.toRoute<CodePostScreenRoute>()

    val codePostState = codePostRepository
        .getCodePost(id = route.id)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DataState.Loading
        )


    fun deleteCodePost() {
        codePostRepository.deleteCodePost(id = route.id)
            .onEachState(
                onLoading = { stateToLoading() },
                onError = { stateToError(it) },
                onSuccess = { stateToSuccess() },
            )
            .launchIn(viewModelScope)
    }
}

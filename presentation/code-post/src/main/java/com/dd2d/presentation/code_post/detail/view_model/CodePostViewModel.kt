package com.dd2d.presentation.code_post.detail.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.domain.code_post.repository.CodePostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class CodePostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val codePostRepository: CodePostRepository,
): ViewModel(), UIStateManager {
    override val uiState = MutableStateFlow<UIState>(UIState.Idle)

    private val codePostId = savedStateHandle.getStateFlow(key = "id", -1)
    fun getCodePostId() = codePostId.value


    @OptIn(ExperimentalCoroutinesApi::class)
    val codePostState = codePostId
        .flatMapLatest(codePostRepository::getCodePost)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DataState.Loading
        )

    fun deleteCodePost() {
        codePostRepository.deleteCodePost(id = getCodePostId())
            .onEach { state ->
                when(state) {
                    is DataState.Loading -> stateToLoading()
                    is DataState.Error -> stateToError(state.exception)
                    is DataState.Success -> stateToSuccess()
                }
            }
            .launchIn(viewModelScope)
    }
}

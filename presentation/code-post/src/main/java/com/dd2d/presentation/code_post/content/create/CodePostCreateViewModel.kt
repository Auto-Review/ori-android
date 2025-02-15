package com.dd2d.presentation.code_post.content.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.domain.code_post.model.CodePostCreator
import com.dd2d.domain.code_post.repository.CodePostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class CodePostCreateViewModel @Inject constructor(
    private val codePostRepository: CodePostRepository
): ViewModel(), UIStateManager {
    override val uiState = MutableStateFlow<UIState>(UIState.Idle)

    fun create(request: CodePostCreator) {
        codePostRepository.createCodePost(create = request)
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

package com.dd2d.presentation.code_post.content.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.core.state.onEachState
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.domain.code_post.repository.CodePostRepository
import com.dd2d.presentation.code_post.model.CodePostCreateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
internal class CodePostCreateViewModel @Inject constructor(
    private val codePostRepository: CodePostRepository
): ViewModel(), UIStateManager {
    override val uiState = MutableStateFlow<UIState>(UIState.Idle)

    val createState = CodePostCreateState()

    fun create() {
        codePostRepository.createCodePost(create = createState.toCodePostCreator())
            .onEachState(
                onLoading = { stateToLoading() },
                onError = { stateToError(it) },
                onSuccess = { stateToSuccess() }
            )
            .launchIn(viewModelScope)
    }
}

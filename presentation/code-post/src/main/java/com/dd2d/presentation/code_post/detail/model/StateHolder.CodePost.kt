package com.dd2d.presentation.code_post.detail.model

import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.onEachState
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.stateToError
import com.dd2d.core.presentation.state.stateToLoading
import com.dd2d.core.presentation.state.stateToSuccess
import com.dd2d.domain.code_post.repository.CodePostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn

internal class CodePostStateHolder(
    private val id: Int,
    private val scope: CoroutineScope,
    private val repository: CodePostRepository,
) {
    private val _deleteState = MutableStateFlow<UIState>(UIState.Idle)
    val deleteState = _deleteState.asStateFlow()

    val codePostState = repository
        .getCodePost(id = id)
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DataState.Loading
        )

    fun deleteCodePost() {
        repository.deleteCodePost(id = id)
            .onEachState(
                onLoading = { _deleteState.stateToLoading() },
                onError = { _deleteState.stateToError(it) },
                onSuccess = { _deleteState.stateToSuccess() },
            )
            .launchIn(scope)
    }

    fun scrap() {}
}

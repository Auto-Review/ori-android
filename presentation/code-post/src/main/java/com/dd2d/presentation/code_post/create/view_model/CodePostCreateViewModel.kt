package com.dd2d.presentation.code_post.create.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.presentation.state.onError
import com.dd2d.core.presentation.state.onLoadingStateChanged
import com.dd2d.core.presentation.state.onSuccess
import com.dd2d.core.presentation.state.withStatefulResult
import com.dd2d.domain.code_post.repository.CodePostRepository
import com.dd2d.presentation.code_post.create.model.CodePostCreateState
import com.dd2d.presentation.code_post.create.model.CreateResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
internal class CodePostCreateViewModel @Inject constructor(
    private val codePostRepository: CodePostRepository
): ViewModel() {
    val createState = CodePostCreateState()

    private val _createResult = MutableSharedFlow<CreateResult>()
    val createResult = _createResult.asSharedFlow()

    fun create() {
        codePostRepository
            .withStatefulResult { createCodePost(create = createState.toCodePostCreator()) }
            .onLoadingStateChanged { createState.isCreating = it }
            .onError { _createResult.emit(CreateResult.Error(cause = it)) }
            .onSuccess { _createResult.emit(CreateResult.Success(codePostId = it)) }
            .launchIn(viewModelScope)
    }
}

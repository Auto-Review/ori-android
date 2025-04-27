package com.dd2d.presentation.code_post.review.view_model


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.core.state.onEachState
import com.dd2d.core.core.state.onStateSuccess
import com.dd2d.core.core.state.unWrap
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.core.presentation.state.stateToError
import com.dd2d.core.presentation.state.stateToLoading
import com.dd2d.core.presentation.state.stateToSuccess
import com.dd2d.domain.code_post.repository.CodePostReviewRepository
import com.dd2d.domain.user.repository.UserRepository
import com.dd2d.presentation.code_post.review.CodePostReviewCreateScreenRoute
import com.dd2d.presentation.code_post.review.model.ReviewInputState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class CodePostReviewCreateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    userRepository: UserRepository,
    private val codePostReviewRepository: CodePostReviewRepository,
) : ViewModel(), UIStateManager {
    override val uiState = MutableStateFlow<UIState>(UIState.Idle)
    val route = savedStateHandle.toRoute<CodePostReviewCreateScreenRoute>()

    private val user = userRepository.me()
        .unWrap(default = null)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null,
        )

    val inputState = ReviewInputState()
    fun create() {
        codePostReviewRepository
            .createCodePostReview(creator = inputState.toCreator(codePostId = route.codePostId))
            .onEachState(
                onLoading = { uiState.stateToLoading() },
                onError = { uiState.stateToError(it) },
                onSuccess = {
                    uiState.stateToSuccess()
                },
            )
            .launchIn(viewModelScope)
    }

    fun update() {
        if(user.value == null || route.reviewId == null) return

        val updater = inputState.toUpdater(user.value!!, route.reviewId)
        codePostReviewRepository.updateCodePostReview(updater = updater)
            .onEachState(
                onLoading = { uiState.stateToLoading() },
                onError = { uiState.stateToError(it) },
                onSuccess = { uiState.stateToSuccess() },
            )
            .launchIn(viewModelScope)
    }

    init {
        route.reviewId?.let { id ->
            codePostReviewRepository.getCodePostReview(id)
                .onStateSuccess(inputState::updateOrigin)
                .launchIn(viewModelScope)
        }
    }
}
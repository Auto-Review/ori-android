package com.dd2d.presentation.code_post.detail.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.onEachState
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.stateToError
import com.dd2d.core.presentation.state.stateToLoading
import com.dd2d.core.presentation.state.stateToSuccess
import com.dd2d.domain.code_post.repository.CodePostCommentRepository
import com.dd2d.domain.code_post.repository.CodePostRepository
import com.dd2d.domain.code_post.repository.CodePostReviewRepository
import com.dd2d.domain.user.repository.UserRepository
import com.dd2d.presentation.code_post.detail._navigation.CodePostScreenRoute
import com.dd2d.presentation.code_post.detail.model.CommentStateHolder
import com.dd2d.presentation.code_post.detail.model.ReviewStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class CodePostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    userRepository: UserRepository,
    private val codePostRepository: CodePostRepository,
    reviewRepository: CodePostReviewRepository,
    commentRepository: CodePostCommentRepository,
): ViewModel() {
    private val route = savedStateHandle.toRoute<CodePostScreenRoute>()

    val userState = userRepository.me()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DataState.Loading
        )

    val codePostState = codePostRepository
        .getCodePost(id = route.id)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DataState.Loading
        )

    private val _deleteState = MutableStateFlow<UIState>(UIState.Idle)
    val deleteState = _deleteState.asStateFlow()

    fun deleteCodePost() {
        codePostRepository.deleteCodePost(route.id)
            .onEachState(
                onLoading = { _deleteState.stateToLoading() },
                onError = { _deleteState.stateToError(it) },
                onSuccess = { _deleteState.stateToSuccess() },
            )
            .launchIn(viewModelScope)
    }

    val commentStateHolder = CommentStateHolder(
        id = route.id,
        scope = viewModelScope,
        getCommentListFlow = commentRepository::getCodePostCommentList,
        createCommentFlow = commentRepository::createCodePostComment,
        updateCommentFlow = commentRepository::updateCodePostComment,
        deleteCommentFlow = commentRepository::deleteCodePostComment,
    )

    val reviewStateHolder = ReviewStateHolder(
        id = route.id,
        scope = viewModelScope,
        getReviewListFlow = reviewRepository::getCodePostReviewList,
        createReviewFlow = reviewRepository::createCodePostReview,
        deleteReviewFlow = reviewRepository::deleteCodePostReview,
    )
}

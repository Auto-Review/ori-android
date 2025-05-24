package com.dd2d.presentation.code_post.detail.view_model

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.state.ActionResult
import com.dd2d.core.presentation.state.Stateful
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.onError
import com.dd2d.core.presentation.state.onLoadingStateChanged
import com.dd2d.core.presentation.state.onSuccess
import com.dd2d.core.presentation.state.withStatefulResult
import com.dd2d.domain.auth_user.user.model.User
import com.dd2d.domain.auth_user.user.repository.UserRepository
import com.dd2d.domain.code_post.repository.CodePostCommentRepository
import com.dd2d.domain.code_post.repository.CodePostRepository
import com.dd2d.domain.code_post.repository.CodePostReviewRepository
import com.dd2d.presentation.code_post.detail._navigation.CodePostScreenRoute
import com.dd2d.presentation.code_post.detail.model.CommentStateHolder
import com.dd2d.presentation.code_post.detail.model.ReviewStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
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
    val route = savedStateHandle.toRoute<CodePostScreenRoute>()

    val userState = userRepository.me()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DataState.Loading
        )

    val codePostState = codePostRepository
        .withStatefulResult { getCodePost(id = route.id) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Stateful.Loading
        )

    private val _deleteState = MutableStateFlow<UIState>(UIState.Idle)
    val deleteState = _deleteState.asStateFlow()


    private val _deleteResult = MutableSharedFlow<ActionResult<Unit>>()
    val deleteResult = _deleteResult.asSharedFlow()
    var isDeleting by mutableStateOf(false)
    val canDelete by derivedStateOf {
        val userId = (userState.value as? DataState.Success<User>)?.data?.id
        val codePostAuthorId = (codePostState.value as? Stateful.Success)?.data?.author?.id
        userId == codePostAuthorId
    }
    fun deleteCodePost() {
        codePostRepository.withStatefulResult { deleteCodePost(route.id) }
            .onLoadingStateChanged { isDeleting = it }
            .onError { _deleteResult.emit(ActionResult.Failure(it)) }
            .onSuccess { _deleteResult.emit(ActionResult.Success(Unit)) }
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
        codePostId = route.id,
        scope = viewModelScope,
        getReviewListFlow = reviewRepository::getCodePostReviewList,
        deleteReviewFlow = reviewRepository::deleteCodePostReview,
    )
}

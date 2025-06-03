package com.dd2d.presentation.code_post.detail.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.action.CommonActionResultBus
import com.dd2d.core.presentation.state.Stateful
import com.dd2d.core.presentation.state.onError
import com.dd2d.core.presentation.state.onLoadingStateChanged
import com.dd2d.core.presentation.state.onSuccess
import com.dd2d.core.presentation.state.withStatefulResult
import com.dd2d.domain.auth_user.user.model.User
import com.dd2d.domain.auth_user.user.repository.UserRepository
import com.dd2d.domain.code_post.model.post.CodePost
import com.dd2d.domain.code_post.repository.CodePostCommentRepository
import com.dd2d.domain.code_post.repository.CodePostRepository
import com.dd2d.domain.code_post.repository.CodePostReviewRepository
import com.dd2d.domain.code_post.repository.CodePostScrapRepository
import com.dd2d.presentation.code_post.detail._navigation.CodePostScreenRoute
import com.dd2d.presentation.code_post.detail.model.CodePostDeleteCancelResult
import com.dd2d.presentation.code_post.detail.model.CodePostDeleteSuccessResult
import com.dd2d.presentation.code_post.detail.model.CommentStateHolder
import com.dd2d.presentation.code_post.detail.model.ReviewStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CodePostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    userRepository: UserRepository,
    private val codePostRepository: CodePostRepository,
    private val codePostScrapRepository: CodePostScrapRepository,
    reviewRepository: CodePostReviewRepository,
    commentRepository: CodePostCommentRepository,
): ViewModel() {
    val actionBus = CommonActionResultBus()

    val route = savedStateHandle.toRoute<CodePostScreenRoute>()

    val userState = userRepository.me()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DataState.Loading
        )

    val codePostState = codePostRepository
        .withStatefulResult { getCodePost(id = route.id) }
        .onSuccess { data -> isScrapped = data.isScrapped }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Stateful.Loading
        )

    val isAuthor = combine(
        flow = userState.filterIsInstance<DataState.Success<User>>(),
        flow2 = codePostState.filterIsInstance<Stateful.Success<CodePost>>(),
        transform = { user, codePost ->
            user.data.id == codePost.data.author.id
        }
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    var isScrapped by mutableStateOf(false)
    fun toggleScrap() {
        codePostScrapRepository
            .withStatefulResult { toggleScrap(codePostId = route.id) }
            .onSuccess { isScrapped = !isScrapped }
            .launchIn(viewModelScope)
    }

    var isDeleting by mutableStateOf(false)
    private var deleteJob: Job? = null
    fun deleteCodePost() {
        deleteJob = codePostRepository
            .withStatefulResult {
                delay(3000)
                deleteCodePost(route.id)
            }
            .onLoadingStateChanged { isDeleting = it }
            .onError(actionBus::emitFailure)
            .onSuccess { actionBus.newResult(CodePostDeleteSuccessResult()) }
            .onCompletion { isDeleting = false }
            .launchIn(viewModelScope)
    }

    fun cancelDelete() {
        viewModelScope.launch {
            deleteJob?.cancelAndJoin()
            deleteJob = null
            actionBus.newResult(CodePostDeleteCancelResult())
        }
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

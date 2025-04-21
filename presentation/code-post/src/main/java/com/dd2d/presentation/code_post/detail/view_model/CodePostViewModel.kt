package com.dd2d.presentation.code_post.detail.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.core.state.DataState
import com.dd2d.domain.code_post.repository.CodePostCommentRepository
import com.dd2d.domain.code_post.repository.CodePostRepository
import com.dd2d.domain.code_post.repository.CodePostReviewRepository
import com.dd2d.domain.user.repository.UserRepository
import com.dd2d.presentation.code_post.detail._navigation.CodePostScreenRoute
import com.dd2d.presentation.code_post.detail.model.CodePostStateHolder
import com.dd2d.presentation.code_post.detail.model.CommentStateHolder
import com.dd2d.presentation.code_post.detail.model.ReviewStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class CodePostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    userRepository: UserRepository,
    codePostRepository: CodePostRepository,
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
    val codePostStateHolder = CodePostStateHolder(
        id = route.id,
        scope = viewModelScope,
        repository = codePostRepository
    )

    val commentStateHolder = CommentStateHolder(
        id = route.id,
        scope = viewModelScope,
        repository = commentRepository
    )

    val reviewStateHolder = ReviewStateHolder(
        id = route.id,
        scope = viewModelScope,
        repository = reviewRepository
    )
}

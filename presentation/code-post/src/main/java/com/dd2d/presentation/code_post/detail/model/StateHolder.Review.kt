package com.dd2d.presentation.code_post.detail.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd2d.core.core.state.onEachState
import com.dd2d.core.core.state.onStateSuccess
import com.dd2d.core.presentation.state.stateToError
import com.dd2d.core.presentation.state.stateToLoading
import com.dd2d.core.presentation.state.stateToSuccess
import com.dd2d.domain.code_post.model.review.CodePostReview
import com.dd2d.domain.code_post.model.review.CodePostReviewDeleter
import com.dd2d.domain.code_post.repository.CodePostReviewRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn

internal class ReviewStateHolder(
    private val id: Int,
    private val scope: CoroutineScope,
    private val repository: CodePostReviewRepository,
) {
    var reviewList by mutableStateOf<List<CodePostReview>>(emptyList())
        private set
    fun refresh() {
        repository
            .getCodePostReviewList(codePostId = id)
            .onStateSuccess { reviewList += this }
            .launchIn(scope)
    }


    val createState = ReviewCreateState()
    fun registerReview() {
        repository
            .createCodePostReview(creator = createState.toCreator(codePostId = id))
            .onEachState(
                onLoading = { createState.uiState.stateToLoading() },
                onError = { createState.uiState.stateToError(it) },
                onSuccess = { createState.uiState.stateToSuccess() },
            )
            .launchIn(scope)
    }

    fun updateReview() {

    }

    fun deleteReview(reviewId: Int, authorEmail: String) {
        repository.deleteCodePostReview(deleter = CodePostReviewDeleter(id = id, email = authorEmail))
            .onStateSuccess { reviewList = reviewList.filter { it.id != reviewId } }
            .launchIn(scope)
    }

    init {
        refresh()
    }
}


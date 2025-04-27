package com.dd2d.presentation.code_post.detail.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.onStateSuccess
import com.dd2d.domain.code_post.model.review.CodePostReview
import com.dd2d.domain.code_post.model.review.CodePostReviewDeleter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn

internal class ReviewStateHolder(
    private val codePostId: Int,
    private val scope: CoroutineScope,
    private val getReviewListFlow: (id: Int) -> Flow<DataState<List<CodePostReview>>>,
    private val deleteReviewFlow: (deleter: CodePostReviewDeleter) -> Flow<DataState<Unit>>,
) {
    var reviewList by mutableStateOf<List<CodePostReview>>(emptyList())
        private set
    fun refresh() {
        getReviewListFlow(codePostId)
            .onStateSuccess { reviewList += this }
            .launchIn(scope)
    }

    fun deleteReview(reviewId: Int, authorEmail: String) {
        deleteReviewFlow(CodePostReviewDeleter(id = reviewId, email = authorEmail))
            .onStateSuccess { reviewList = reviewList.filter { it.id != reviewId } }
            .launchIn(scope)
    }

    init {
        refresh()
    }
}


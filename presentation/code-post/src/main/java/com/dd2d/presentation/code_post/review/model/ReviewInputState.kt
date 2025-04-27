package com.dd2d.presentation.code_post.review.model

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd2d.domain.code_post.model.review.CodePostReview
import com.dd2d.domain.code_post.model.review.CodePostReviewCreator
import com.dd2d.domain.code_post.model.review.CodePostReviewUpdater
import com.dd2d.domain.user.model.User

internal class ReviewInputState {
    var origin by mutableStateOf<CodePostReview?>(null)
        private set
    fun updateOrigin(value: CodePostReview) {
        origin = value
        review = value.review
        code = value.code
    }

    var review by mutableStateOf("")
    var code by mutableStateOf("")

    val canSubmit by derivedStateOf {
        val default = review.isNotBlank() || code.isNotBlank()

        if(origin == null) default
        else default && (review != origin!!.review || code != origin!!.code)
    }

    fun toCreator(codePostId: Int): CodePostReviewCreator {
        return CodePostReviewCreator(
            codePostId = codePostId,
            review = review,
            code = code,
        )
    }
    fun toUpdater(user: User, reviewId: Int): CodePostReviewUpdater {
        return CodePostReviewUpdater(
            id = reviewId,
            email = user.email,
            review = review,
            code = code,
        )
    }
}
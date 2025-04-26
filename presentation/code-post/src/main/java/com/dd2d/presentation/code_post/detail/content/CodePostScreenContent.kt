package com.dd2d.presentation.code_post.detail.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.code_post.model.post.CodePost
import com.dd2d.domain.user.model.User
import com.dd2d.presentation.code_post.detail.component.CodeComponent
import com.dd2d.presentation.code_post.detail.component.CodePostHeaderComponent
import com.dd2d.presentation.code_post.detail.component.CodePostLevelComponent
import com.dd2d.presentation.code_post.detail.component.CommentComposition
import com.dd2d.presentation.code_post.detail.component.ReviewListComponent
import com.dd2d.presentation.code_post.detail.model.CodePostStateHolder
import com.dd2d.presentation.code_post.detail.model.CommentStateHolder
import com.dd2d.presentation.code_post.detail.model.ReviewStateHolder
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

@Composable
internal fun CodePostScreenContent(
    user: User?,
    codePost: CodePost,
    codePostStateHolder: CodePostStateHolder,
    reviewStateHolder: ReviewStateHolder,
    commentStateHolder: CommentStateHolder,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { reviewStateHolder.reviewList.size + 1 }
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .imePadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        CodePostLevelComponent(level = codePost.level)
        ReviewListComponent(
            controllable = codePost.author.id == user?.id,
            reviewList = reviewStateHolder.reviewList,
            focusedReviewIndex = if(pagerState.currentPage == 0) null else pagerState.currentPage - 1,
            onReviewClick = { index ->
                scope.launch {
                    if(index == null) pagerState.scrollToPage(0)
                    else pagerState.scrollToPage(index + 1)
                }
            },
            onCreateClick = { },
            onEditClick = {},
            onDeleteClick = {
                if(user == null) return@ReviewListComponent

                reviewStateHolder.reviewList
                    .getOrNull(pagerState.currentPage - 1)
                    ?.let { target ->
                        reviewStateHolder.deleteReview(reviewId = target.id, authorEmail = user.email)
                    }
            },
        )
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            pageSpacing = 16.dp,
        ) { page ->
            if(page == 0) {
                Column {
                    CodePostHeaderComponent(codePost = codePost)
                    CodeComponent(code = codePost.code)
                }
            }
            else {
                val review = reviewStateHolder.reviewList[page - 1]
                Column {
                    CodePostHeaderComponent(codePost = codePost.copy(description = review.review))
                    CodeComponent(code = codePost.code.copy(content = review.code))
                }
            }
        }
        CommentComposition(
            user = user,
            commentStateHolder = commentStateHolder,
        )
    }
}



@Preview(showBackground = true)
@Composable
private fun CodePostScreenContentPrev() {
    val id = 1
    val scope = rememberCoroutineScope()

    val codePostStateHolder = CodePostStateHolder(id, scope, { emptyFlow() }, { emptyFlow() })
    val reviewStateHolder = ReviewStateHolder(id, scope, { emptyFlow() }, { emptyFlow() }, { emptyFlow() })
    val commentStateHolder = CommentStateHolder(id, scope, { emptyFlow() }, { emptyFlow() }, { emptyFlow() })

    AppTheme {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            CodePostScreenContent(
                user = User.dummy(),
                codePost = CodePost.dummy(),
                codePostStateHolder = codePostStateHolder,
                reviewStateHolder = reviewStateHolder,
                commentStateHolder = commentStateHolder,
                modifier = Modifier
            )
        }
    }
}
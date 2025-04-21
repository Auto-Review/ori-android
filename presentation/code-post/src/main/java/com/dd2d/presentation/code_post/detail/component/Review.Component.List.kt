package com.dd2d.presentation.code_post.detail.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.core.model.DateTimeString
import com.dd2d.core.presentation.dialog.CancellableConfirmDialog
import com.dd2d.core.presentation.icon.VectorIconButton
import com.dd2d.core.presentation.main_text.Main700Text
import com.dd2d.core.presentation.option_selector.OptionSelector
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.code_post.model.review.CodePostReview

@Composable
internal fun ReviewListComponent(
    controllable: Boolean,
    reviewList: List<CodePostReview>,
    focusedReviewIndex: Int?,
    onReviewClick: (index: Int?) -> Unit,
    onDeleteClick: () -> Unit,
    onCreateClick: () -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var openDeleteConfirmDialog by remember { mutableStateOf(false) }
    if(reviewList.isNotEmpty()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(top = 14.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(horizontal = 12.dp, vertical = 15.dp)
        ) {
            reviewList.forEachIndexed { index, item ->
                ReviewItem(
                    controllable = controllable,
                    reviewDate = item.createdAt,
                    isFocused = index == focusedReviewIndex,
                    onClick = {
                        if(index == focusedReviewIndex) onReviewClick(null)
                        else onReviewClick(index)
                    },
                    onDelete = { openDeleteConfirmDialog = true },
                    onEditClick = onEditClick,
                )
            }
            if(controllable) {
                Main700Text(
                    text = "리뷰 추가",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 12.sp,
                    lineHeight = 16.8.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable(onClick = onCreateClick)
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }
    }

    if(openDeleteConfirmDialog) {
        CancellableConfirmDialog(
            title = "리뷰를 삭제하시겠습니까?",
            message = "삭제된 리뷰는 복구할 수 없습니다.",
            onCancel = { openDeleteConfirmDialog = false },
            onConfirm = {
                openDeleteConfirmDialog =  false
                onDeleteClick()
            }
        )
    }

}

@Composable
private fun ReviewItem(
    controllable: Boolean,
    reviewDate: DateTimeString,
    isFocused: Boolean,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var open3DotsMenu by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = reviewDate,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            lineHeight = 16.8.sp,
            fontWeight = if(isFocused) FontWeight.W700 else FontWeight.W400,
            textDecoration = if(isFocused) TextDecoration.Underline else null,
            modifier = Modifier.clickable(onClick = onClick)
        )
        if(controllable && isFocused) {
            OptionSelector(
                open = open3DotsMenu,
                close = { open3DotsMenu = false },
                options = listOf("수정", "삭제"),
                onOptionSelected = { index ->
                    open3DotsMenu = false
                    when(index) {
                        0 -> onEditClick()
                        1 -> onDelete()
                    }
                },
                parentContent = {
                    VectorIconButton(
                        icon = Icons.Default.MoreVert,
                        modifier = Modifier.size(16.dp)
                    ) {
                        open3DotsMenu = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewListComponentPrev() {
    var focus by remember { mutableStateOf<Int?>(0) }
    AppTheme {
        ReviewListComponent(
            controllable = true,
            reviewList = List(4) { CodePostReview.dummy(it) },
//            reviewList = List(0) { CodePostReview.dummy(it) },
            focusedReviewIndex = focus,
            onReviewClick = { focus = it },
            onDeleteClick = {},
            onCreateClick = {},
            onEditClick = {},
            modifier = Modifier
        )
    }
}
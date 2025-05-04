package com.dd2d.presentation.code_post.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.presentation._ori.PageController
import com.dd2d.core.presentation.dialog.CancellableConfirmDialog
import com.dd2d.core.presentation.main_text.Main600Text
import com.dd2d.core.presentation.state.UIState
import com.dd2d.domain.auth_user.user.model.User
import com.dd2d.presentation.code_post.detail.model.CommentStateHolder

@Composable
internal fun CommentComposition(
    user: User?,
    commentStateHolder: CommentStateHolder,
    modifier: Modifier = Modifier,
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val commentState by commentStateHolder.uiState.collectAsStateWithLifecycle()

    var openReportConfirmDialog by remember { mutableStateOf(false) }
    var deleteCommentId by remember { mutableStateOf<Int?>(null) }

    Column(modifier = modifier) {
        Main600Text(
            text = "COMMENTS ${commentStateHolder.commentListManager.list.size}",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            lineHeight = 16.8.sp,
        )
        CommentInputFieldComponent(
            nickname = user?.nickname,
            content = commentStateHolder.inputState.content,
            onContentChange = { commentStateHolder.inputState.content = it },
            canCommit = commentStateHolder.inputState.canCommit,
            onCommit = {
                keyboard?.hide()
                commentStateHolder.commitComment()
            },
            isCommiting = commentState is UIState.Loading
        )
        commentStateHolder.commentListManager.list
            .forEach { item ->
                key(item.id) {
                    CommentListItemComponent(
                        isMine = item.author.id == user?.id,
                        item = item,
                        onReport = { openReportConfirmDialog = true },
                        onEdit = { commentStateHolder.inputState.prepareUpdate(item) },
                        onDelete = { deleteCommentId = item.id },
                    )
                    HorizontalDivider()
                }
            }
        PageController(
            currentPage = commentStateHolder.commentListManager.options.page,
            lastPage = commentStateHolder.commentListManager.totalPage,
            requestPage = commentStateHolder::pageTo,
            modifier = Modifier.padding(top = 12.dp)
        )

    }

    if(openReportConfirmDialog) {
        CancellableConfirmDialog(
            title = "해당 댓글을 신고하시겠습니까?",
            message = null,
            onCancel = { openReportConfirmDialog = false },
            onConfirm = {
                openReportConfirmDialog = false
                commentStateHolder.report()
            }
        )
    }

    if(user != null && deleteCommentId != null) {
        CancellableConfirmDialog(
            title = "댓글을 삭제하시겠습니까?",
            message = null,
            onCancel = { deleteCommentId = null },
            onConfirm = {
                commentStateHolder.deleteComment(commentId = deleteCommentId!!, authorId = user.id)
                deleteCommentId = null
            }
        )
    }
}
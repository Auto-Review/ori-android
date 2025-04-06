package com.dd2d.presentation.code_post.detail.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.domain.code_post.model.comment.CodePostCommentCreator
import kotlinx.coroutines.flow.MutableStateFlow

internal class CommentCreateState: UIStateManager {
    override val uiState = MutableStateFlow<UIState>(UIState.Idle)

    var parentId: Int? = null
    var content by mutableStateOf("")
    var isPublic by mutableStateOf(true)
    var mentionNickName by mutableStateOf<String?>(null)
    var mentionEmail by mutableStateOf<String?>(null)

    fun toCreator(codePostId: Int): CodePostCommentCreator {
        return CodePostCommentCreator(
            postId = codePostId,
            parentId = parentId,
            content = content,
            isPublic = isPublic,
            mentionNickName = mentionNickName,
            mentionEmail = mentionEmail,
        )
    }
}
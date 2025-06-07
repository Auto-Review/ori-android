package com.dd2d.presentation.code_post.detail.model

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd2d.domain.code_post.model.comment.CodePostCommentCreator
import com.dd2d.domain.code_post.model.comment.CodePostCommentListItem
import com.dd2d.domain.code_post.model.comment.CodePostCommentUpdater

internal class CommentInputState(private val codePostId: Int) {
  var updateCommentId by mutableStateOf<Int?>(null)

  var parentId: Int? = null
  var content by mutableStateOf("")
  var isPublic by mutableStateOf(true)
  var mentionNickName by mutableStateOf<String?>(null)
  var mentionEmail by mutableStateOf<String?>(null)

  val canCommit by derivedStateOf {
    content.isNotBlank()
  }

  fun reset() {
    updateCommentId = null
    parentId = null
    content = ""
    isPublic = true
    mentionNickName = null
    mentionEmail = null
  }

  fun prepareUpdate(target: CodePostCommentListItem) {
    updateCommentId = target.id
    parentId = target.parentCommentId
    content = target.content
    isPublic = true
    mentionNickName = target.mentionNickname
    mentionEmail = target.mentionEmail
  }

  fun toCreator(): CodePostCommentCreator {
    return CodePostCommentCreator(
      postId = codePostId,
      parentId = parentId,
      content = content,
      isPublic = isPublic,
      mentionNickName = mentionNickName,
      mentionEmail = mentionEmail,
    )
  }

  fun toUpdater(): CodePostCommentUpdater {
    if (updateCommentId == null) throw NullPointerException("updateCommentId is null")

    return CodePostCommentUpdater(
      commentId = updateCommentId!!,
      content = content,
      isPublic = isPublic,
      mentionNickName = mentionNickName,
      mentionEmail = mentionEmail,
    )
  }
}
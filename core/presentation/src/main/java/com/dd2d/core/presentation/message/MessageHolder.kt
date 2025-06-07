package com.dd2d.core.presentation.message

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MessageHolder(private val coroutineScope: CoroutineScope) {
  private val _messages = MutableSharedFlow<Message>()
  val messages = _messages.asSharedFlow()

  fun dialogOf(scope: DialogMessageScope.() -> Unit) {
    val buildScope = DialogMessageScopeImpl().apply(scope)

    check(!(buildScope.title.isNullOrEmpty() && buildScope.content.isNullOrEmpty())) {
      "메시지의 제목과 내용은 모두 [null]이거나 [공백]일 수 없습니다."
    }

    check(buildScope.actions.isNotEmpty()) {
      "액션은 적어도 하나 이상 있어야 합니다."
    }

    val message = Message.Dialog(
      title = buildScope.title,
      content = buildScope.content,
      actions = buildScope.actions
    )
    coroutineScope.launch { _messages.emit(message) }
  }

  fun defaultDialogOf(scope: DialogMessageScope.() -> Unit) {
    dialogOf {
      scope()
      addAction { text = "확인" }
    }
  }


  fun snackbarOf(scope: SnackbarMessageScope.() -> Unit) {
    val buildScope = SnackbarMessageScopeImpl().apply(scope)
    check(buildScope.content.isNotEmpty()) {
      "메시지의 내용은 [공백]일 수 없습니다."
    }

    check(
      (buildScope.action == null && buildScope.actionLabel == null)
          || (buildScope.action != null && buildScope.actionLabel != null)
    ) {
      "메시지의 [Action]과 [ActionLabel]을 모두 입력해 주세요."
    }

    val message = Message.Snackbar(
      content = buildScope.content,
      actionLabel = buildScope.actionLabel,
      action = buildScope.action,
      onDismiss = buildScope.onDismiss,
      duration = buildScope.duration
    )
    coroutineScope.launch { _messages.emit(message) }
  }
}

@Composable
fun rememberMessageHolder(
  coroutineScope: CoroutineScope = rememberCoroutineScope()
): MessageHolder {
  return remember { MessageHolder(coroutineScope) }
}


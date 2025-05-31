package com.dd2d.core.presentation.message

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import com.dd2d.core.presentation.theme.MainColor
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
}

@Composable
fun rememberMessageHolder(
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): MessageHolder {
    return remember { MessageHolder(coroutineScope) }
}

private class DialogMessageActionScopeImpl: DialogMessageActionScope {
    override var text: String = ""
    override var onClick: (dismissRequest: () -> Unit) -> Unit = { it() }
    override var textColor: Color = MainColor
    override var backgroundColor: Color = Color.Transparent
    override var ratio: Float = 1F
}

private class DialogMessageScopeImpl: DialogMessageScope {
    override var title: String? = null
    override var content: String? = null
    override var actions: MutableList<Message.Dialog.Action> = mutableListOf()

    override fun addAction(action: Message.Dialog.Action) {
        actions.add(action)
    }

    override fun addAction(scope: DialogMessageActionScope.() -> Unit) {
        val buildScope = DialogMessageActionScopeImpl().apply(scope)
        actions.add(
            Message.Dialog.Action(
                text = buildScope.text,
                onClick = { dismissRequest -> buildScope.onClick(dismissRequest) },
                textColor = buildScope.textColor,
                backgroundColor = buildScope.backgroundColor,
                ratio = buildScope.ratio,
            )
        )
    }
}
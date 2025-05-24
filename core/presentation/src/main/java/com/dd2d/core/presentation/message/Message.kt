package com.dd2d.core.presentation.message

import androidx.compose.ui.graphics.Color

@DslMarker
private annotation class DialogMessageScopeDsl

@DialogMessageScopeDsl
interface DialogMessageScope {
    var title: String?
    var content: String?
    var actions: MutableList<Message.Dialog.Action>
    fun addAction(action: Message.Dialog.Action)
    fun addAction(scope: DialogMessageActionScope.() -> Unit)
}

@DialogMessageScopeDsl
interface DialogMessageActionScope {
    var text: String
    var onClick: (dismissRequest: () -> Unit) -> Unit
    var textColor: Color
    var backgroundColor: Color
    var ratio: Float
}


sealed interface Message {
    data class Dialog(
        val title: String?,
        val content: String?,
        val actions: List<Action>
    ): Message {
        data class Action(
            val text: String,
            val onClick: (onDismiss: () -> Unit) -> Unit,
            val textColor: Color,
            val backgroundColor: Color,
            val ratio: Float,
        )
    }
}
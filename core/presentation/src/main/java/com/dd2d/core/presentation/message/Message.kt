package com.dd2d.core.presentation.message

import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.graphics.Color

sealed interface Message {
    data class Dialog(
        override val title: String?,
        override val content: String?,
        override val actions: List<Action>
    ): Message, DialogMessageField {
        data class Action(
            val text: String,
            val onClick: (onDismiss: () -> Unit) -> Unit,
            val textColor: Color,
            val backgroundColor: Color,
            val ratio: Float,
        )
    }

    data class Snackbar(
        override val content: String,
        override val actionLabel: String?,
        override val action: (() -> Unit)?,
        override val onDismiss: (() -> Unit)?,
        override val duration: SnackbarDuration,
    ): Message, SnackbarMessageField
}

interface SnackbarMessageField {
    val content: String
    val actionLabel: String?
    val action: (() -> Unit)?
    val onDismiss: (() -> Unit)?
    val duration: SnackbarDuration
}

interface SnackbarMessageScope: SnackbarMessageField {
    override var content: String
    override var actionLabel: String?
    override var action: (() -> Unit)?
    override var onDismiss: (() -> Unit)?
    override var duration: SnackbarDuration
}

internal class SnackbarMessageScopeImpl: SnackbarMessageScope {
    override var content: String = ""
    override var actionLabel: String? = null
    override var action: (() -> Unit)? = null
    override var onDismiss: (() -> Unit)? = null
    override var duration: SnackbarDuration = SnackbarDuration.Short
}
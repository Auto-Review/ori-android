package com.dd2d.core.presentation.message

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun MessageHandler(
    messageHolder: MessageHolder,
    messageHandlerType: MessageHandlerType = DefaultMessageHandlerType
) {
    var message by remember { mutableStateOf<Message?>(null) }

    LaunchedEffect(key1 = messageHolder) {
        messageHolder.messages.collect { msg ->
            message = msg
        }
    }

    message?.let { msg ->
        when(msg) {
            is Message.Dialog -> {
                messageHandlerType.DialogType(message = msg, onDismiss = { message = null })
            }
        }
    }
}
package com.dd2d.core.presentation.message

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
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
  val snackbarHostState = remember { SnackbarHostState() }

  SnackbarHost(snackbarHostState)
  LaunchedEffect(key1 = messageHolder) {
    messageHolder.messages.collect { msg ->
      when (msg) {
        is Message.Snackbar -> {
          val snackbarResult = snackbarHostState.showSnackbar(
            message = msg.content,
            actionLabel = msg.actionLabel,
            withDismissAction = msg.onDismiss != null,
            duration = msg.duration,
          )
          when (snackbarResult) {
            SnackbarResult.Dismissed -> msg.onDismiss?.invoke()
            SnackbarResult.ActionPerformed -> msg.action?.invoke()
          }
        }

        else -> message = msg
      }
    }
  }

  message?.let { msg ->
    when (msg) {
      is Message.Dialog -> {
        messageHandlerType.DialogType(message = msg, onDismiss = { message = null })
      }

      else -> {
        /** do nothing */
      }
    }
  }
}
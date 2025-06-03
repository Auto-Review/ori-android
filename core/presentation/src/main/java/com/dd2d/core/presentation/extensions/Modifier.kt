package com.dd2d.core.presentation.extensions

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/** 이전 클릭 시간을 저장한 후 [delay]만큼 클릭 이벤트를 호출하지 않음.*/
fun Modifier.delayedClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    delay: Duration = 5.seconds,
    onClick: () -> Unit,
): Modifier = then(
    Modifier.composed {
        var lastClickTime by remember(key1 = delay) { mutableLongStateOf(0L) }
        var inDelay by remember { mutableStateOf(false) }
        clickable(
            enabled = enabled && !inDelay,
            onClickLabel = onClickLabel,
            role = role,
            onClick = {
                val clickTime = System.currentTimeMillis()
                inDelay = lastClickTime + delay.inWholeMilliseconds > clickTime
                if(!inDelay) {
                    lastClickTime = clickTime
                    onClick()
                }
            },
        )
    }
)

/** 이전 클릭 시간을 저장한 후 [delay]만큼 클릭 이벤트를 호출하지 않음.*/
fun Modifier.delayedClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    delay: Duration = 5.seconds,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = null,
    onClick: () -> Unit,
): Modifier = then(
    Modifier.composed {
        var lastClickTime by remember(key1 = delay) { mutableLongStateOf(0L) }
        clickable(
            enabled = enabled,
            onClickLabel = onClickLabel,
            role = role,
            interactionSource = interactionSource,
            indication = indication,
            onClick = {
                val clickTime = System.currentTimeMillis()
                if(lastClickTime + delay.inWholeMilliseconds < clickTime) {
                    lastClickTime = clickTime
                    onClick()
                }
            },
        )
    }
)
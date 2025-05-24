package com.dd2d.core.presentation.message

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.main_text.Main500Text
import com.dd2d.core.presentation.main_text.Main600Text

interface MessageHandlerType {
    @Composable
    fun DialogType(
        message: Message.Dialog,
        onDismiss: () -> Unit
    )
}

object DefaultMessageHandlerType: MessageHandlerType {
    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    override fun DialogType(message: Message.Dialog, onDismiss: () -> Unit) {
        Dialog(onDismissRequest = onDismiss) {
            Column(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(24.dp)
            ) {
                message.title?.let { title ->
                    Main600Text(
                        text = title,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                message.content?.let { content ->
                    if (message.title != null) {
                        Spacer(Modifier.height(16.dp))
                    }
                    Main400Text(
                        text = content,
                        fontSize = 16.sp,
                        maxLine = Int.MAX_VALUE,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                FlowRow(
                    maxItemsInEachRow = 3,
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    message.actions.forEach { action ->
                        Main500Text(
                            text = action.text,
                            color = action.textColor,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .clickable(onClick = { action.onClick(onDismiss) })
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}
package com.dd2d.presentation.code_post.component

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.dd2d.core.presentation.icon.VectorIcon
import com.dd2d.core.presentation.theme.AppTheme

@Composable
internal fun CodeLevelSlider(
    level: Int,
    onLevelChange: (Int) -> Unit,
    maxLevel: Int = 5,
    onLevelColor: Color = MaterialTheme.colorScheme.primary,
    offLevelColor: Color = onLevelColor.copy(alpha = 0.5F)
) {
    var dragStartOffsetX by remember { mutableFloatStateOf(0F) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = {
                        dragStartOffsetX = 0F
                        onLevelChange(0)
                    },
                    onDragEnd = {
                        dragStartOffsetX = 0F
                    },
                    onDragCancel = {
                        dragStartOffsetX = 0F
                    },
                    onHorizontalDrag = { _, amount ->
                        val new = (dragStartOffsetX + amount).coerceIn(minimumValue = 0F, maximumValue = size.width.toFloat())
                        dragStartOffsetX = new
                        onLevelChange((new / size.width * maxLevel).toInt() + 1)
                    }
                )
            }
    ) {
        repeat(maxLevel) { index ->
            VectorIcon(
                icon = Icons.Default.Star,
                tint = if(level >= index + 1) onLevelColor else offLevelColor,
                modifier = Modifier
            )
        }
    }
}

@Preview
@Preview(locale = "ko")
@Composable
private fun CodeLevelSliderPrev() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
        ) {
            CodeLevelSlider(
                level = 3,
                onLevelChange = {},
            )
        }
    }
}
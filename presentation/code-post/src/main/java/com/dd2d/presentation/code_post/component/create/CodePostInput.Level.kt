package com.dd2d.presentation.code_post.component.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.image.PainterImage
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.presentation.code_post.R

@Composable
internal fun CodePostLevelInput(
    level: Int,
    onLevelChange: (value: Int) -> Unit,
    maxLevel: Int,
    modifier: Modifier = Modifier
) {
    val starSize = 33.dp
    InputItem(label = "난이도", modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            repeat(maxLevel) { index ->
                PainterImage(
                    res = if(level >= index + 1) R.drawable.star_fill else R.drawable.star,
                    modifier = Modifier.size(starSize)
                        .clickable(indication = null, interactionSource = null) {
                            onLevelChange(index + 1)
                        }
                )
            }
        }
    }
}

@Preview
@Composable
private fun CodePostLevelInputPrev() {
    var level by remember { mutableStateOf(0) }
    AppTheme {
        CodePostLevelInput(
            level = level,
            onLevelChange = {level = it},
            maxLevel = 5,
            modifier = Modifier
        )
    }
}
package com.dd2d.presentation.code_post.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.image.PainterImage
import com.dd2d.presentation.code_post.R

@Composable
internal fun CodePostLevelComponent(
    level: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(5) { index ->
            PainterImage(
                res = if(level >= index + 1) R.drawable.star_fill else R.drawable.star,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
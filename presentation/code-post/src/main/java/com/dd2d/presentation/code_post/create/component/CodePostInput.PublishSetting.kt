package com.dd2d.presentation.code_post.create.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation._ori.InputItem

@Composable
internal fun PublishSettingInput(
    isPublic: Boolean,
    onIsPublicChange: (value: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    InputItem(label = "공개 설정", modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            SelectableLabel(
                label = "공개",
                isSelected = isPublic,
                onClick = { onIsPublicChange(true) },
                modifier = Modifier.weight(1F)
            )
            SelectableLabel(
                label = "비공개",
                isSelected = !isPublic,
                onClick = { onIsPublicChange(false) },
                modifier = Modifier.weight(1F)
            )
        }
    }
}
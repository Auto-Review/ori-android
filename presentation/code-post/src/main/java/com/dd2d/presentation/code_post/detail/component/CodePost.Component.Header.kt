package com.dd2d.presentation.code_post.detail.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.domain.code_post.model.post.CodePost

@Composable
internal fun CodePostHeaderComponent(
    codePost: CodePost,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 12.dp, vertical = 15.dp)
    ) {
        Main400Text(
            text = codePost.author.nickname,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            lineHeight = 16.8.sp,
        )
        Main400Text(
            text = codePost.description,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            lineHeight = 16.8.sp,
            maxLine = Int.MAX_VALUE,
            modifier = Modifier.padding(top = 4.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Main400Text(
                text = codePost.code.language,
                color = MaterialTheme.colorScheme.surfaceBright,
                fontSize = 12.sp,
                lineHeight = 16.8.sp,
            )
            Main400Text(
                text = "TODO : 공개 설정",
                color = MaterialTheme.colorScheme.surfaceBright,
                fontSize = 12.sp,
                lineHeight = 16.8.sp,
            )
        }
    }
}
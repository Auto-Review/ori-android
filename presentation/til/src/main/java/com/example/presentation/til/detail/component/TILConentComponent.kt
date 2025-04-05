package com.example.presentation.til.detail.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.til.model.TIL

@Composable
internal fun TILContentComponent(
    til: TIL,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 300.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 14.dp, vertical = 17.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Main400Text(
                text = "${til.author.nickname}   ${til.createdAt}",
                color = Color.Black,
                fontSize = 12.sp,
                lineHeight = 16.8.sp,
            )
            Main400Text(
                text = "[공개범위]",
                color = MaterialTheme.colorScheme.surfaceBright,
                fontSize = 12.sp,
                lineHeight = 16.8.sp,
            )
        }

        Main400Text(
            text = til.content,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            lineHeight = 16.8.sp,
            maxLine = Int.MAX_VALUE,
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TILContentComponentPrev() {
    AppTheme {
        TILContentComponent(
            til = TIL.dummy(),
            modifier = Modifier
        )
    }
}
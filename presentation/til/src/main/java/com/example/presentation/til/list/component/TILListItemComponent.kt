package com.example.presentation.til.list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main500Text
import com.dd2d.domain.til.model.TILListItem

@Composable
internal fun TILListItemComponent(
    item: TILListItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp)
    ) {
        Main500Text(
            text = item.title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Main500Text(
            text = "${item.author.nickname}  ${item.createdAt} RE : [댓글 개수]",
            color = MaterialTheme.colorScheme.surfaceBright,
            fontSize = 10.sp,
            lineHeight = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
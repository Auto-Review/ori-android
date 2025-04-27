package com.dd2d.core.presentation._ori

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main400Text


@Composable
fun PageController(
    currentPage: Int,
    lastPage: Int,
    requestPage: (page: Int) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5F),
) {
    val visiblePages = remember(currentPage, lastPage) {
        if(lastPage == 0) return@remember emptyList()
        mutableSetOf<Int>().apply {
            add(0)
            if((currentPage - 1) > 0) add(currentPage - 1)
            add(currentPage)
            if((currentPage + 1) in 1 until lastPage) add(currentPage + 1)
            add(lastPage-1)
        }.toList()
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .then(modifier)
    ) {
        for(i in visiblePages.indices) {
            val page = visiblePages[i]
            PageButton(
                page = page,
                onClick = { requestPage(page) },
                shape = shape,
                color = if(page == currentPage) selectedColor else unSelectedColor
            )
            visiblePages.getOrNull(i + 1)?.let { nextPage ->
                if(page + 1 != nextPage) {
                    Main400Text(
                        text = "...",
                        fontSize = 12.sp,
                    )
                }
            }
        }
    }
}

@Composable
private fun PageButton(
    page: Int,
    onClick: () -> Unit,
    shape: Shape,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(24.dp)
            .widthIn(min = 24.dp)
            .clip(shape)
            .border(width = 1.dp, color = color, shape = shape)
            .clickable(onClick = onClick)
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .then(modifier)
    ) {
        Main400Text(
            text = "${page + 1}",
            fontSize = 12.sp,
            color = color
        )
    }
}
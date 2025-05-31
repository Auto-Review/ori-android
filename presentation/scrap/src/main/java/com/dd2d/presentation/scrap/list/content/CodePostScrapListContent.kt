package com.dd2d.presentation.scrap.list.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dd2d.core.presentation.list.item.PostListItemComponent
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.core.presentation.list.v2.RefreshLazyColumn
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.domain.code_post.model.scrap.CodePostScrapListOption

@ExperimentalMaterial3Api
@Composable
internal fun CodePostScrapListContent(
    listController: LazyListController<CodePostScrapListOption, CodePostListItem>,
    onDetailClick: (tilId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    RefreshLazyColumn(
        controller = listController,
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = listController.list,
            key = CodePostListItem::id
        ) { item ->
            PostListItemComponent(
                title = item.title,
                authorName = item.author.nickname,
                createdAt = item.createdAt,
                commentCount = null,
                onClick = { onDetailClick(item.id) },
                modifier = Modifier.animateItem().fillMaxWidth(),
            )
            HorizontalDivider()
        }
    }
}
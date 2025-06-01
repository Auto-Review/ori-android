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
import com.dd2d.domain.code_post.model.scrap.CodePostScrapListItem
import com.dd2d.domain.code_post.model.scrap.CodePostScrapListOption

@ExperimentalMaterial3Api
@Composable
internal fun CodePostScrapListContent(
    listController: LazyListController<CodePostScrapListOption, CodePostScrapListItem>,
    onDetailClick: (tilId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    RefreshLazyColumn(
        controller = listController,
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = listController.list,
            key = CodePostScrapListItem::id
        ) { item ->
            PostListItemComponent(
                title = item.codePostTitle,
                authorName = item.writer,
                createdAt = item.createdAt,
                commentCount = item.commentCount,
                onClick = { onDetailClick(item.codePostId) },
                modifier = Modifier
                    .animateItem()
                    .fillMaxWidth(),
            )
            HorizontalDivider()
        }
    }
}
package com.dd2d.core.presentation.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <ListItem: Any> PagerColumn(
    pager: LazyPagingItems<ListItem>,
    modifier: Modifier = Modifier,
    lazyState: LazyListState = rememberLazyListState(),
    refreshState: PullToRefreshState = rememberPullToRefreshState(),
    indicator: @Composable (BoxScope.() -> Unit) = {
        RefreshLazyListIndicator(
            isRefreshing = pager.loadState.refresh == LoadState.Loading,
            refreshState = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    },
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    enableMoveTopButton: Boolean = true,
    content: LazyListScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    val isVisibleMoveTopButton by remember(key1 = lazyState) {
        derivedStateOf { lazyState.canScrollBackward && !lazyState.isScrollInProgress }
    }

    PullToRefreshBox(
        state = refreshState,
        isRefreshing = pager.loadState.refresh == LoadState.Loading,
        onRefresh = pager::refresh,
        indicator = indicator,
        modifier = modifier
    ) {
        LazyColumn(
            state = lazyState,
            contentPadding = contentPadding,
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            userScrollEnabled = userScrollEnabled,
            reverseLayout = reverseLayout,
            flingBehavior = flingBehavior,
            content = content,
            modifier = Modifier.matchParentSize()
        )
        AnimatedVisibility(
            visible = enableMoveTopButton && isVisibleMoveTopButton,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .navigationBarsPadding()
                .offset(y = (-20).dp)
                .align(Alignment.BottomCenter)
                .size(30.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "맨 위로 이동",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.surface)
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
                    .clickable { scope.launch { lazyState.animateScrollToItem(0) } }
            )
        }
    }
}

fun <T: Any> LazyListScope.pagerItems(
    pager: LazyPagingItems<T>,
    key: (item: T) -> Any,
    scope: @Composable LazyItemScope.(item: T) -> Unit
) {
    items(
        count = pager.itemCount,
        key = { key(pager[it]!!) }
    ) { index ->
        scope(pager[index]!!)
    }
}

fun <T: Any> LazyListScope.pagerItemsIndexed(
    pager: LazyPagingItems<T>,
    key: (index: Int, item: T) -> Any,
    scope: @Composable LazyItemScope.(index: Int, item: T) -> Unit
) {
    items(
        count = pager.itemCount,
        key = { key(it, pager[it]!!) }
    ) { index ->
        scope(index, pager[index]!!)
    }
}
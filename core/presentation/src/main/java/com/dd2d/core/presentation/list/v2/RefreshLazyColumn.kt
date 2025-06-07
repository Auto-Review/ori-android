@file:OptIn(ExperimentalMaterial3Api::class)

package com.dd2d.core.presentation.list.v2

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dd2d.core.core.model.Pageable
import com.dd2d.core.presentation.list.RefreshLazyListIndicator
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn

@Composable
fun <Option : Pageable<Option>, ListItem : Any> RefreshLazyColumn(
  controller: LazyListController<Option, ListItem>,
  modifier: Modifier = Modifier,
  lazyState: LazyListState = rememberLazyListState(),
  refreshState: PullToRefreshState = rememberPullToRefreshState(),
  indicator: @Composable (BoxScope.() -> Unit) = {
    RefreshLazyListIndicator(
      isRefreshing = (controller.state as? LazyListControllerState.Loading)?.loadType == LoadType.Refresh,
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
  content: LazyListScope.() -> Unit,
) {
  var isLoading by remember { mutableStateOf(false) }
  val isRefreshing by remember(controller) {
    derivedStateOf {
      (controller.state as? LazyListControllerState.Loading)?.loadType == LoadType.Refresh
    }
  }

  LaunchedEffect(key1 = Unit) {
    snapshotFlow { controller.state }
      .collect { state ->
        if (state is LazyListControllerState.Idle) {
          isLoading = false
        }
      }

  }

  LaunchedEffect(key1 = Unit) {
    val lastIndexFlow = snapshotFlow {
      lazyState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
    }

    snapshotFlow { controller.loadNextPageTriggerIndex }
      .filterNotNull()
      .filter { controller.canLoadNext }
      .combine(
        flow = lastIndexFlow.filterNotNull(),
        transform = { triggerIndex, lastIndex ->
          if (!isLoading && lastIndex >= triggerIndex) {
            isLoading = true
            controller.nextPage()
          }
        }
      )
      .launchIn(this)
  }

  PullToRefreshBox(
    state = refreshState,
    isRefreshing = isRefreshing,
    onRefresh = controller::refresh,
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
  }
}
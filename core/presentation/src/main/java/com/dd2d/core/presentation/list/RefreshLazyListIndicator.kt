package com.dd2d.core.presentation.list

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxScope.RefreshLazyListIndicator(
  isRefreshing: Boolean,
  refreshState: PullToRefreshState,
  modifier: Modifier = Modifier
) {
  Indicator(
    modifier = modifier.align(Alignment.TopCenter),
    isRefreshing = isRefreshing,
    color = MaterialTheme.colorScheme.primary,
    containerColor = MaterialTheme.colorScheme.surfaceContainer,
    state = refreshState
  )
}
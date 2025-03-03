package com.dd2d.core.presentation.main_tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.theme.hansType
import kotlinx.coroutines.launch

@Composable
fun MainPagerTab(
    pagerState: PagerState,
    tabs: List<String>,
    modifier: Modifier = Modifier,
    pageSpacing: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(),
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    pageContent: @Composable PagerScope.(page: Int) -> Unit,
) {
    val scope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ){
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
            indicator = { tabPositions ->
                if(pagerState.currentPage < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        color = contentColor,
                        height = 3.dp,
                        modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    )
                }
            },
            containerColor = containerColor,
            contentColor = contentColor,
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = index == pagerState.currentPage,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                ) {
                    Text(
                        text = tab,
                        style = hansType.tabTitle,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            key = { index -> tabs[index] },
            contentPadding = contentPadding,
            pageContent = pageContent,
            pageSpacing = pageSpacing,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
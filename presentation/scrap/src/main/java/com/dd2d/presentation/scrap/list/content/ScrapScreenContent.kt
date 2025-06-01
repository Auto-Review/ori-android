package com.dd2d.presentation.scrap.list.content

import android.util.Log
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dd2d.core.core.model.Pagination
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.core.presentation.main_tab.MainPagerTab
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.core.presentation.theme.LocalHansType
import com.dd2d.core.presentation.theme.hansType
import com.dd2d.domain.code_post.model.scrap.CodePostScrapListItem
import com.dd2d.domain.code_post.model.scrap.CodePostScrapListOption
import com.dd2d.domain.til.model.TILListItem
import com.dd2d.domain.til.model.TILScrapListOption
import com.dd2d.presentation.scrap.list.component.ScrapTopBar

private enum class Tabs(val label: String) {
    Code(label = "CODE"),
    TIL(label = "TIL"),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ScrapScreenContent(
    onClose: () -> Unit,
    codePostScrapListController: LazyListController<CodePostScrapListOption, CodePostScrapListItem>,
    onCodePostClick: (codePostId: Int) -> Unit,
    tilScrapListController: LazyListController<TILScrapListOption, TILListItem>,
    onTILClick: (tilId: Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Log.d("LOG_CHECK", "ScrapScreenContent: ${codePostScrapListController.state}")
    val pagerState = rememberPagerState { Tabs.entries.size }

    Scaffold(
        topBar = { ScrapTopBar(onBack = onClose) },
        modifier = modifier.fillMaxSize()
    ) { inner ->
        MainPagerTab(
            pagerState = pagerState,
            tabs = Tabs.entries.map(Tabs::label),
            modifier = Modifier
                .consumeWindowInsets(inner)
                .fillMaxSize()
                .padding(inner)
        ) { page ->
            when(page) {
                Tabs.Code.ordinal -> {
                    CodePostScrapListContent(
                        listController = codePostScrapListController,
                        onDetailClick = onCodePostClick,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Tabs.TIL.ordinal -> {
                    TILScrapListContent(
                        listController = tilScrapListController,
                        onDetailClick = onTILClick,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ScrapScreenContentPrev() {
    val scope = rememberCoroutineScope()
    val codePostList = remember {
        Pagination(
            list = List(40) {
                CodePostScrapListItem.dummy(it)
            },
            currentPage = 1,
            totalPage = 10,
            totalItemCount = 40,
        )
    }
    val tilList = remember {
        Pagination(
            list = List(40) {
                TILListItem.dummy(it)
            },
            currentPage = 1,
            totalPage = 10,
            totalItemCount = 40,
        )
    }
    AppTheme {
        CompositionLocalProvider(
            LocalHansType provides hansType
        ) {

            ScrapScreenContent(
                onClose = {},
                codePostScrapListController = LazyListController(
                    option = CodePostScrapListOption(),
                    scope = scope,
                    getList = { Result.success(codePostList) },
                ),
                onCodePostClick = {},
                tilScrapListController = LazyListController(
                    option = TILScrapListOption(),
                    scope = scope,
                    getList = { Result.success(tilList) },
                ),
                onTILClick = {},
                modifier = Modifier
            )
        }
    }
}
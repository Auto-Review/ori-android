package com.dd2d.core.presentation.scaffold

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.message.MessageHandler
import com.dd2d.core.presentation.message.MessageHolder

@Composable
fun MessageHandlerScaffold(
    messageHolder: MessageHolder,
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = { MessageHandler(messageHolder) },
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = content,
    )
}

@Composable
fun MessageHandlerBottomBarScaffold(
    bottomBar: @Composable RowScope.() -> Unit,
    messageHolder: MessageHolder,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.background,
    topBar: @Composable () -> Unit = {},
    visibleBottomBar: Boolean = true,
    bottomBarEnter: EnterTransition = slideInVertically { it } + expandVertically(),
    bottomBarExit: ExitTransition = slideOutVertically { it } + shrinkVertically(),
    scrollState: ScrollState = rememberScrollState(),
    contentPadding: PaddingValues = PaddingValues(24.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable ColumnScope.() -> Unit,
) {
    Scaffold(
        snackbarHost = { MessageHandler(messageHolder) },
        containerColor = containerColor,
        topBar = topBar,
        bottomBar = {
            AnimatedVisibility(
                visible = visibleBottomBar,
                enter = bottomBarEnter,
                exit = bottomBarExit,
            ) {
                BottomAppBar(
                    containerColor = Color.Transparent,
                    contentPadding = PaddingValues(
                        horizontal = contentPadding.calculateStartPadding(LayoutDirection.Rtl),
                        vertical = 8.dp
                    ),
                    content = bottomBar,
                    modifier = Modifier.imePadding()
                )
            }
        },
        modifier = modifier
    ) { inner ->
        Column(
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement,
            content = content,
            modifier = Modifier
                .consumeWindowInsets(inner)
                .fillMaxSize()
                .padding(inner)
                .verticalScroll(state = scrollState)
                .padding(contentPadding)
        )
    }
}
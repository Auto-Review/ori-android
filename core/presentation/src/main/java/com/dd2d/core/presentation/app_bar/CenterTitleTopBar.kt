package com.dd2d.core.presentation.app_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.icon.BackIcon
import com.dd2d.core.presentation.icon.CloseIcon
import com.dd2d.core.presentation.main_text.Main500Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTitleTopBar(
    title: String,
    onBack: (() -> Unit)?,
    modifier: Modifier = Modifier,
    useCloseIcon: Boolean = false,
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors =  TopAppBarDefaults.centerAlignedTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Main500Text(
                text = title,
                fontSize = 18.sp
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    if(useCloseIcon) CloseIcon()
                    else BackIcon()
                }
            }
        },
        actions = actions,
        colors = colors,
        scrollBehavior = scrollBehavior,
    )
}
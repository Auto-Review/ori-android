package com.dd2d.core.presentation.app_bar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.icon.BackIcon
import com.dd2d.core.presentation.icon.BookmarkIcon
import com.dd2d.core.presentation.main_text.Main700Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTapBar(
    title: String,
    onBack: () -> Unit,
    onScrap: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Main700Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 20.sp,
                lineHeight = 24.sp,
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                BackIcon()
            }
        },
        actions = {
            IconButton(onClick = onScrap) {
                BookmarkIcon(onBookMark = listOf(true, false).random())
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        modifier = modifier.fillMaxWidth(),
    )
}
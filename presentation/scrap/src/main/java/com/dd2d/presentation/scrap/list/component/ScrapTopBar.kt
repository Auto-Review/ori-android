@file:OptIn(ExperimentalMaterial3Api::class)

package com.dd2d.presentation.scrap.list.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dd2d.core.presentation.icon.VectorIconButton
import com.dd2d.core.presentation.theme.LocalHansType

@Composable
internal fun ScrapTopBar(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "//SCRAP",
                style = LocalHansType.current.topBarTitle
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        actions = {
            VectorIconButton(
                icon = Icons.Default.Close,
                onClick = onClose,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground,
                )
            )
        },
        modifier = modifier
    )
}
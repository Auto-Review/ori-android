@file:OptIn(ExperimentalMaterial3Api::class)

package com.dd2d.presentation.scrap.list.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dd2d.core.presentation.icon.BackIcon
import com.dd2d.core.presentation.theme.LocalHansType

@Composable
internal fun ScrapTopBar(
  onBack: () -> Unit,
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
    navigationIcon = {
      IconButton(onClick = onBack) {
        BackIcon(tint = MaterialTheme.colorScheme.onBackground)
      }
    },
    modifier = modifier
  )
}
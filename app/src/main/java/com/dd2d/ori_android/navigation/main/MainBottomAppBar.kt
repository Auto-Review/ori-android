package com.dd2d.ori_android.navigation.main

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.dd2d.core.presentation.icon.VectorIcon
import com.dd2d.core.presentation.main_text.Main400Text

data class BottomBarItem(
    val iconRes: Int,
    val labelRes: Int?,
    val destination: MainScreenDestination
)

@Composable
fun MainBottomBar(
    items: List<BottomBarItem>,
    onClick: (destination: MainScreenDestination) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    itemColors: NavigationBarItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
        indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.7F),
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
    ),
    initialIndex: Int = 0,
    disableWhenSelected: Boolean = true,
    alwaysShowLabel: Boolean = true
) {
    var selectedIndex by remember { mutableIntStateOf(initialIndex) }

    BottomAppBar(
        containerColor = containerColor,
        contentColor = contentColor,
        modifier = modifier
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    if(selectedIndex != index || !disableWhenSelected) {
                        selectedIndex = index
                        onClick(item.destination)
                    }
                },
                icon = {
                    VectorIcon(res = item.iconRes)
                },
                label = {
                    item.labelRes?.let { res ->
                        Main400Text(
                            text = stringResource(id = res),
                        )
                    }
                },
                alwaysShowLabel = alwaysShowLabel,
                colors = itemColors,
            )
        }
    }
}
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
    val destination: Any
)

@Composable
fun MainBottomBar(
    items: List<BottomBarItem>,
    onClick: (destination: Any) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    itemColors: NavigationBarItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.background,
        selectedTextColor = MaterialTheme.colorScheme.background,
        indicatorColor = Color.Transparent,
        unselectedIconColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5F),
        unselectedTextColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5F),
        disabledIconColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5F),
        disabledTextColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5F),
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
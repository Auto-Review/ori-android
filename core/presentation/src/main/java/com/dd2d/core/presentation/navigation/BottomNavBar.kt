package com.dd2d.core.presentation.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.dd2d.core.presentation.icon.VectorIcon
import com.dd2d.core.presentation.theme.tp

@Composable
fun BottomNavBar(
	navController: NavController,
	items: List<BottomBarItem>,
	initialTabIndex: Int,
	colors: NavigationBarItemColors,
	modifier: Modifier = Modifier,
) {
	var currentTab by rememberSaveable { mutableIntStateOf(initialTabIndex) }

	NavigationBar(
		containerColor = MaterialTheme.colorScheme.onSurface,
		modifier = modifier
	) {
		items.forEachIndexed { index, item ->
			val isSelected = currentTab == index

			val iconRes = if(isSelected) item.selectedIconRes else item.iconRes
			val labelRes = if(isSelected) item.selectedLabelRes else item.labelRes

			NavigationBarItem(
				selected = isSelected,
				onClick = {
					if(!isSelected) {
						currentTab = index
						item.navigate(navController)
					}
			    },
				enabled = item.enabled,
				icon = { iconRes?.let { VectorIcon(res = iconRes) } },
				label = {
					labelRes?.let {
						Text(
							text = stringResource(labelRes),
							color = Color.Unspecified,
							fontSize = 10.tp,
							lineHeight = 12.tp,
							fontWeight = if(isSelected) FontWeight.W700 else FontWeight.W500
						)
					}
				},
				colors = colors
			)
		}
	}
}
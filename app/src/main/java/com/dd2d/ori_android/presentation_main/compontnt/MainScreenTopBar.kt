package com.dd2d.ori_android.presentation_main.compontnt

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.dd2d.core.presentation.icon.VectorIconButton
import com.dd2d.core.presentation.image.PainterImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreenTopBar(
    appNavController: NavController,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { PainterImage(res = com.dd2d.core.presentation.R.drawable.logo) },
        actions = {
            VectorIconButton(res = com.dd2d.core.presentation.R.drawable.search) {

            }
            VectorIconButton(res = com.dd2d.core.presentation.R.drawable.scrap) {

            }
            VectorIconButton(res = com.dd2d.core.presentation.R.drawable.off_notification) {

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        modifier = modifier
    )
}
package com.dd2d.ori_android.presentation_main.compontnt

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.dd2d.core.presentation.icon.AddIcon
import com.dd2d.core.presentation.option_selector.OptionSelector

@Composable
internal fun MainScreenFAB(
    options: List<String>,
    onOptionClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var open by remember { mutableStateOf(false) }
    OptionSelector(
        open = open,
        close = { open = false },
        options = options,
        onOptionSelected = {
            open = false
            onOptionClick(it)
        },
    ) {
        FloatingActionButton(
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            onClick = { open = !open },
            content = { AddIcon() },
            modifier = modifier
        )
    }
}
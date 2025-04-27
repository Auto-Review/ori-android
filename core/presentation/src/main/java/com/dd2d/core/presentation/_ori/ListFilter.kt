package com.dd2d.core.presentation._ori

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.icon.VectorIcon
import com.dd2d.core.presentation.option_selector.OptionSelector
import com.dd2d.core.presentation.slot_main_text.SlotMain500Text

@Composable
fun ListFilter(
    currentValue: String,
    values: List<String>,
    onValueClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var open by remember { mutableStateOf(false) }
    OptionSelector(
        open = open,
        close = { open = !open },
        options = values,
        onOptionSelected = {
            open = false
            onValueClick(it)
        },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        SlotMain500Text(
            text = currentValue,
            color = Color.White,
            fontSize = 12.sp,
            lineHeight = 24.sp,
            letterSpacing = 2.sp,
            suffixStartPadding = 4.dp,
            suffix = {
                VectorIcon(
                    icon = Icons.Default.KeyboardArrowDown,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            },
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .clickable { open = !open }
                .padding(horizontal = 10.dp, vertical = 8.dp)
        )
    }
}
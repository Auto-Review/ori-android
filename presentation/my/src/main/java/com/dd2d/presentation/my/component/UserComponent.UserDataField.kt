package com.dd2d.presentation.my.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main500Text
import com.dd2d.core.presentation.main_text.Main700Text


@Composable
internal fun UserDataField(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Main700Text(
            text = label,
            fontSize = 12.sp,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth(0.2F)
        )
        Main500Text(
            text = value,
            fontSize = 12.sp,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

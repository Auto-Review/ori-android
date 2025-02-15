package com.dd2d.core.presentation.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.R
import com.dd2d.core.presentation.main_text.Main500Text

@Composable
fun ConfirmDialog(
    title: String,
    message: String?,
    cancelText: String = stringResource(R.string.cancel),
    confirmText: String = stringResource(R.string.confirm),
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
) {
    MainDialog(
        title = title,
        message = message,
        onDismiss = onCancel,
        dismissButton = {
            Main500Text(
                text = cancelText,
                fontSize = 16.sp,
                modifier = Modifier
                    .clickable(onClick = onConfirm)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
        },
        confirmButton = {
            Main500Text(
                text = confirmText,
                fontSize = 16.sp,
                modifier = Modifier
                    .clickable(onClick = onConfirm)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }
    )
}
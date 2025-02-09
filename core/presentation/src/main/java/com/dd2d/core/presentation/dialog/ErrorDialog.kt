package com.dd2d.core.presentation.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.core.exception.ClientException
import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.presentation.main_text.Main500Text

@Composable
fun ErrorDialog(
    exception: ManagedException,
    onConfirm: () -> Unit,
) {
    MainDialog(
        title = "오류",
        message = exception.message,
        onDismiss = onConfirm,
        confirmButton = {
            Main500Text(
                text = "확인",
                fontSize = 16.sp,
                modifier = Modifier
                    .clickable(onClick = onConfirm)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }
    )
}

@Preview
@Preview(locale = "ko")
@Composable
private fun ErrorDialogPrev() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ) {
        ErrorDialog(
            exception = ClientException.UnknownException(),
            onConfirm = {}
        )
    }
}
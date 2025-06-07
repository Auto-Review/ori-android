package com.dd2d.core.presentation.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.core.exception.ClientException
import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.presentation.BuildConfig
import com.dd2d.core.presentation.R
import com.dd2d.core.presentation.main_text.Main500Text

@Composable
fun ErrorDialog(
  exception: ManagedException,
  title: String = stringResource(R.string.error),
  confirmText: String = stringResource(R.string.confirm),
  onConfirm: () -> Unit,
) {
  val message =
    if (BuildConfig.DEBUG) {
      "${exception.message}\n${exception.cause}"
    } else {
      exception.message
    }

  MainDialog(
    title = title,
    message = message,
    onDismiss = onConfirm,
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

@Composable
fun ErrorDialog(
  throwable: Throwable,
  title: String = stringResource(R.string.error),
  confirmText: String = stringResource(R.string.confirm),
  onConfirm: () -> Unit,
) {
  val message =
    if (BuildConfig.DEBUG) {
      "${throwable.message}\n${throwable.cause}"
    } else {
      throwable.message
    }

  MainDialog(
    title = title,
    message = message,
    onDismiss = onConfirm,
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
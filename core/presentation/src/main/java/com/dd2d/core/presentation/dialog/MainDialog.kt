package com.dd2d.core.presentation.dialog

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.main_text.Main600Text
import com.dd2d.core.presentation.theme.AppTheme

@Composable
fun MainDialog(
  title: String,
  message: String?,
  onDismiss: () -> Unit,
  modifier: Modifier = Modifier,
  containerColor: Color = MaterialTheme.colorScheme.background,
  textAlign: TextAlign = TextAlign.Start,
  shape: Shape = RoundedCornerShape(15.dp),
  dismissButton: @Composable (() -> Unit)? = null,
  confirmButton: @Composable () -> Unit,
) {

  AlertDialog(
    modifier = modifier,
    shape = shape,
    title = {
      Main600Text(
        text = title,
        fontSize = 18.sp,
        textAlign = textAlign,
        modifier = Modifier.fillMaxWidth()
      )
    },
    containerColor = containerColor,
    text = message?.let {
      {
        Main400Text(
          text = message,
          fontSize = 16.sp,
          maxLine = Int.MAX_VALUE,
          textAlign = textAlign,
          modifier = Modifier.fillMaxWidth()
        )
      }
    },
    onDismissRequest = onDismiss,
    dismissButton = dismissButton,
    confirmButton = confirmButton
  )
}

@Preview
@Preview(locale = "ko", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainDialogPrev() {
  AppTheme {
    Column(
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.Start,
      modifier = Modifier
    ) {
      MainDialog(
        title = "title",
//            message = "message",
        message = "sadadsasd\nsdfs \nsdfsdf",
        onDismiss = {},
        modifier = Modifier,
        dismissButton = {
          Main400Text(
            text = "123",
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
              .clickable { }
              .padding(horizontal = 12.dp, vertical = 6.dp)
          )
        },
        confirmButton = {
          Main400Text(text = "123")
        }
      )
    }
  }
}
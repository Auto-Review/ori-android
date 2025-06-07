package com.dd2d.presentation.my.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main500Text
import com.dd2d.core.presentation.main_text.Main700Text

@Composable
internal fun UpdatableUserDataField(
  onEditMode: Boolean,
  label: String,
  originValue: String,
  updatableValueState: TextFieldState,
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
    if (onEditMode) {
      EditField(
        textFieldState = updatableValueState,
        placeholder = "Input $label",
        style = TextStyle(
          fontSize = 12.sp,
          lineHeight = 24.sp,
          color = MaterialTheme.colorScheme.onSurface,
        )
      )
    } else {
      Main500Text(
        text = originValue,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        color = MaterialTheme.colorScheme.onSurface,
      )
    }
  }
}

@Composable
private fun EditField(
  textFieldState: TextFieldState,
  modifier: Modifier = Modifier,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardAction: KeyboardActionHandler? = null,
  placeholder: String? = null,
  style: TextStyle = TextStyle.Default,
) {
  val focus = remember { FocusRequester() }
  LaunchedEffect(key1 = Unit) {
    focus.requestFocus()
  }
  BasicTextField(
    state = textFieldState,
    textStyle = style,
    lineLimits = TextFieldLineLimits.SingleLine,
    keyboardOptions = keyboardOptions,
    onKeyboardAction = keyboardAction,
    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
    decorator = { innerTextField ->
      innerTextField()
      if (textFieldState.text.isEmpty() && placeholder != null) {
        Text(
          text = placeholder,
          style = style.copy(color = style.color.copy(alpha = 0.5F))
        )
      }
    },
    modifier = modifier.focusRequester(focus),
  )
}
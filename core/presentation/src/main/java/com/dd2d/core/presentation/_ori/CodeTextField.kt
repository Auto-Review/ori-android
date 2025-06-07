package com.dd2d.core.presentation._ori

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main400Text

object CodeTextFieldDefault {
  @Composable
  fun DefaultPlaceholder(modifier: Modifier = Modifier) {
    Main400Text(
      text = """
                fun main() {
                    println("Hello World!")
                }
            """.trimIndent(),
      color = Color.LightGray,
      maxLine = Int.MAX_VALUE,
      modifier = modifier
    )
  }
}

@Deprecated("use [CodeEditor]")
@Composable
fun CodeTextField(
  language: String,
  codeTextState: TextFieldState,
  modifier: Modifier = Modifier,
  placeholder: @Composable () -> Unit = { CodeTextFieldDefault.DefaultPlaceholder() }
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(8.dp),
    modifier = Modifier
      .fillMaxWidth()
      .background(
        MaterialTheme.colorScheme.outlineVariant,
        shape = MaterialTheme.shapes.small
      )
      .padding(14.dp)
      .then(modifier)
  ) {
    if (language.isNotBlank()) {
      Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .fillMaxWidth()
      ) {
        Main400Text(text = language, fontSize = 12.sp)
      }
    }
    BasicTextField(
      state = codeTextState,
      lineLimits = TextFieldLineLimits.MultiLine(minHeightInLines = 5),
      textStyle = TextStyle.Default.copy(
        fontSize = 16.sp,
      ),
      decorator = { innerTextField ->
        if (codeTextState.text.isEmpty()) {
          placeholder()
        }
        innerTextField()
      },
      cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
      modifier = Modifier.fillMaxWidth()
    )
  }
}
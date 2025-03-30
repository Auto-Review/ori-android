package com.dd2d.presentation.code_post.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.icon.VectorIconButton
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.main_text_field.MainTextFieldDefaults
import com.dd2d.domain.code_post.model.post.Code
import com.dd2d.presentation.code_post.R

@Composable
internal fun CodeComponent(
    code: Code,
    onCodeChange: ((code: Code) -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val clip = LocalClipboardManager.current

    Surface(
        shape = RoundedCornerShape(5.dp),
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                if(onCodeChange == null) {
                    Main400Text(text = code.language, fontSize = 12.sp)
                }
                else {
                    Main400Text(text = code.language, fontSize = 12.sp)
                }
                VectorIconButton(
                    res = R.drawable.copy,
                    iconSize = 16.dp
                ) {
                    clip.setText(AnnotatedString(code.content))
                }
            }
            TextField(
                value = code.content,
                onValueChange = { new ->
                    onCodeChange?.invoke(code.copy(content = new))
                },
                enabled = onCodeChange != null,
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp,
                ),
                placeholder = {
                    Main400Text(
                        text = """
                            fun main() {
                                println("Hello World!")
                            }
                        """.trimIndent(),
                        color = Color.LightGray,
                        maxLine = Int.MAX_VALUE
                    )
                },
                colors = MainTextFieldDefaults.textFieldColors(
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    errorTextColor = MaterialTheme.colorScheme.onSurface,
                )
            )
        }
    }
}
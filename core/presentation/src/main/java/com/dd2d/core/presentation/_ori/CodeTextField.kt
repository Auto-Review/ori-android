package com.dd2d.core.presentation._ori

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.main_text_field.MainTextFieldDefaults

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

@Composable
fun CodeTextField(
    language: String,
    code: String,
    onCodeChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable () -> Unit = { CodeTextFieldDefault.DefaultPlaceholder() }
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.outlineVariant,
                shape = MaterialTheme.shapes.small
            )
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .then(modifier)
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Main400Text(text = language, fontSize = 12.sp)
        }
        TextField(
            value = code,
            onValueChange = onCodeChange,
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
            ),
            placeholder = placeholder,
            colors = MainTextFieldDefaults.textFieldColors(
                focusedContainerColor = MaterialTheme.colorScheme.outlineVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.outlineVariant,
                disabledContainerColor = MaterialTheme.colorScheme.outlineVariant,
                errorContainerColor = MaterialTheme.colorScheme.outlineVariant,
            )
        )
    }
}
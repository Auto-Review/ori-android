package com.example.presentation.til.create.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.main_text_field.MainTextFieldDefaults

@Composable
internal fun ContentInput(
    content: String,
    onContentChange: (value: String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = content,
        onValueChange = onContentChange,
        placeholder = {
            Main400Text(
                text = "내용을 입력해 주세요.",
                color = MaterialTheme.colorScheme.surfaceBright,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        },
        minLines = 10,
        colors = MainTextFieldDefaults.textFieldColors(),
        modifier = modifier
    )
}
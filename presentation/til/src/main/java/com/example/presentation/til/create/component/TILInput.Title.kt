package com.example.presentation.til.create.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main700Text
import com.dd2d.core.presentation.main_text_field.MainTextFieldDefaults

@Composable
internal fun TitleInput(
    title: String,
    onTitleChange: (value: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboard = LocalSoftwareKeyboardController.current
    TextField(
        value = title,
        onValueChange = onTitleChange,
        placeholder = {
            Main700Text(
                text = "제목",
                color = MaterialTheme.colorScheme.surfaceBright,
                fontSize = 20.sp,
                lineHeight = 24.sp
            )
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.W700
        ),
        colors = MainTextFieldDefaults.textFieldColors(),
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focus ->
                if(!focus.isFocused) {
                    keyboard?.hide()
                }
            }
    )
}
package com.dd2d.presentation.code_post.create.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main700Text
import com.dd2d.core.presentation.main_text_field.MainTextFieldDefaults

@Composable
internal fun CodePostTitleInput(
    title: String,
    onTitleChange: (value: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val fm = LocalFocusManager.current

    DisposableEffect(key1 = fm) {
        onDispose {
            fm.clearFocus(true)
        }
    }
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
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions { fm.clearFocus(force = true) },
        colors = MainTextFieldDefaults.textFieldColors(),
        modifier = modifier.fillMaxWidth()
    )
}
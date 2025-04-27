package com.dd2d.presentation.code_post.create.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dd2d.core.presentation._ori.CodeTextField
import com.dd2d.core.presentation._ori.InputItem

@Composable
internal fun CodePostCodeInput(
    language: String,
    code: String,
    onCodeChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    InputItem(label = "Code", modifier = modifier) {
        CodeTextField(
            language = language,
            code = code,
            onCodeChange = onCodeChange,
            modifier = modifier,
        )
    }
}
package com.dd2d.presentation.code_post.create.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dd2d.core.presentation._ori.InputItem
import com.dd2d.domain.code_post.model.post.Code
import com.dd2d.presentation.code_post._core.component.CodeEditor

@Composable
internal fun CodePostCodeInput(
    language: Code.Language?,
    onLanguageChange: (Code.Language) -> Unit,
    codeTextState: TextFieldState,
    modifier: Modifier = Modifier
) {
    InputItem(label = "Code", modifier = modifier) {
        CodeEditor(
            initialCode = codeTextState.text.toString(),
            onCodeChange = { value ->
                codeTextState.edit { replace(start = 0, end = length, text = value) }
            },
            language = language,
            onLanguageChange = onLanguageChange,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
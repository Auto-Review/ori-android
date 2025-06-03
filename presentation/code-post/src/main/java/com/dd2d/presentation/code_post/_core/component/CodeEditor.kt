package com.dd2d.presentation.code_post._core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.icon.VectorIcon
import com.dd2d.core.presentation.option_selector.OptionSelector2
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.core.presentation.theme.tp
import com.dd2d.domain.code_post.model.post.Code
import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.SyntaxLanguage
import dev.snipme.highlights.model.SyntaxTheme
import dev.snipme.highlights.model.SyntaxThemes
import dev.snipme.kodeview.view.material3.CodeEditText

@Composable
fun CodeEditor(
    initialCode: String,
    onCodeChange: (String) -> Unit,
    language: Code.Language?,
    onLanguageChange: ((Code.Language) -> Unit)?,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    theme: SyntaxTheme = SyntaxThemes.darcula(darkMode = false),
) {
    val languageSyntax = remember(language) {
        SyntaxLanguage.valueOf(language?.name?: SyntaxLanguage.DEFAULT.name)
    }

    var highlights by remember {
        mutableStateOf(
            Highlights.Builder()
                .code(initialCode)
                .theme(theme)
                .language(languageSyntax)
                .build()
        )
    }

    var openLanguageSelector by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .widthIn(min = 100.dp)
                .clickable(indication = null, interactionSource = null) { openLanguageSelector = true }
        ) {
            Text(text = language?.label?: "[select language]", fontSize = 12.tp)
            onLanguageChange?.let {
                VectorIcon(
                    icon = Icons.Default.KeyboardArrowDown,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(15.dp)
                )
                OptionSelector2(
                    open = openLanguageSelector,
                    close = { openLanguageSelector = false },
                    options = Code.Language.entries.map(Code.Language::label),
                    onOptionSelected = { index ->
                        onLanguageChange(Code.Language.entries[index])
                        openLanguageSelector = false
                    },
                )
            }
        }

        CodeEditText(
            highlights = highlights,
            onValueChange = { codeValue ->
                highlights = highlights.getBuilder().code(codeValue).build()
                onCodeChange(codeValue)
            },
            readOnly = readOnly,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            minLines = 10,
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Preview
@Composable
private fun CodeEditorPrev() {
    AppTheme {
        CodeEditor(
            initialCode = """
                int a = 1;
                int b = 2;
                int c = a + b;
            """.trimIndent(),
            onCodeChange = {},
            language = Code.Language.CPP,
            onLanguageChange = {},
            modifier = Modifier
        )
    }
}
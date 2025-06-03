package com.dd2d.presentation.code_post.create.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation._ori.InputItem
import com.dd2d.domain.code_post.model.post.Code

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun CodePostLanguageInput(
    languages: List<Code.Language>,
    selectedLanguage: Code.Language?,
    onSelectedLanguageChange: (value: Code.Language) -> Unit,
    modifier: Modifier = Modifier
) {
    InputItem(label = "사용 언어", modifier = modifier) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            languages.forEach { language ->
                SelectableLabel(
                    label = language.label,
                    isSelected = selectedLanguage == language,
                    onClick = { onSelectedLanguageChange(language) }
                )
            }
        }
    }
}
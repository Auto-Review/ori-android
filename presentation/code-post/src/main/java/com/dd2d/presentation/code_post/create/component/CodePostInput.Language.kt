package com.dd2d.presentation.code_post.create.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation._ori.InputItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun CodePostLanguageInput(
    languages: List<String>,
    selectedLanguage: String,
    onSelectedLanguageChange: (value: String) -> Unit,
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
                    label = language,
                    isSelected = selectedLanguage == language,
                    onClick = { onSelectedLanguageChange(language) }
                )
            }
        }
    }
}
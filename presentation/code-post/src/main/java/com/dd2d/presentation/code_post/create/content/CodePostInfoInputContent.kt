package com.dd2d.presentation.code_post.create.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.presentation.code_post.create.component.CodePostLanguageInput
import com.dd2d.presentation.code_post.create.component.CodePostLevelInput
import com.dd2d.presentation.code_post.create.component.CodePostTitleInput
import com.dd2d.presentation.code_post.create.component.PublishSettingInput
import com.dd2d.presentation.code_post.create.component.ReviewDateInput
import com.dd2d.presentation.code_post.create.model.CodePostCreateState

@Composable
internal fun CodePostInfoInputContent(
    createState: CodePostCreateState,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .imePadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 40.dp)
    ) {
        Column {
            HorizontalDivider()
            CodePostTitleInput(
                title = createState.title,
                onTitleChange = { createState.title = it },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
            )
            HorizontalDivider()
        }
        CodePostLevelInput(
            level = createState.level,
            onLevelChange = { createState.level = it },
            maxLevel = createState.maxLevel,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
        )
        CodePostLanguageInput(
            languages = listOf("java", "kotlin"),
            selectedLanguage = createState.language,
            onSelectedLanguageChange = { createState.language = it },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
        )
        PublishSettingInput(
            isPrivate = createState.isPrivate,
            onIsPrivateChange = { createState.isPrivate = it },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
        )
        ReviewDateInput(
            reviewDate = createState.reviewDate,
            onReviewDateChange = { createState.reviewDate = it },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CodePostInfoInputContentPrev() {
    AppTheme {
        CodePostInfoInputContent(
            createState = remember { CodePostCreateState() },
            modifier = Modifier.fillMaxSize()
        )
    }
}
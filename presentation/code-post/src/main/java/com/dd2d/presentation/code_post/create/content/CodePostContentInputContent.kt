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
import com.dd2d.presentation.code_post.create.component.CodePostCodeInput
import com.dd2d.presentation.code_post.create.component.CodePostDescriptionInput
import com.dd2d.presentation.code_post.create.model.CodePostFormState

@Composable
internal fun CodePostContentInputContent(
  createState: CodePostFormState,
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
    HorizontalDivider()
    CodePostDescriptionInput(
      descriptionTextState = createState.descriptionTextState,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
    )
    CodePostCodeInput(
      language = createState.language,
      onLanguageChange = { createState.language = it },
      codeTextState = createState.codeTextState,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
    )
  }
}

@Preview
@Composable
private fun CodePostContentInputContentPrev() {
  AppTheme {
    CodePostContentInputContent(
      createState = remember { CodePostFormState() },
      modifier = Modifier
    )
  }
}
package com.dd2d.presentation.code_post.review.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation._ori.InputItem
import com.dd2d.core.presentation.main_text_field.MainTextFieldDefaults
import com.dd2d.presentation.code_post._core.component.CodeEditor
import com.dd2d.presentation.code_post.review.model.ReviewInputState

@Composable
internal fun CodePostReviewCreateScreenContent(
  inputState: ReviewInputState,
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
    InputItem(label = "Review", modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 24.dp)) {
      OutlinedTextField(
        value = inputState.review,
        onValueChange = { inputState.review = it },
        minLines = 5,
        shape = MaterialTheme.shapes.small,
        colors = MainTextFieldDefaults.outlineTextFieldColors(),
        modifier = Modifier.fillMaxWidth()
      )
    }
    InputItem(label = "Code", modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 24.dp)) {
      CodeEditor(
        initialCode = inputState.code,
        onCodeChange = { inputState.code = it },
        language = null,
        onLanguageChange = null,
        modifier = Modifier.fillMaxWidth(),
        readOnly = false,
      )
    }
  }
}
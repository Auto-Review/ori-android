package com.example.presentation.til.create.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.theme.AppTheme
import com.example.presentation.til.create.component.ContentInput
import com.example.presentation.til.create.component.PublishSettingInput
import com.example.presentation.til.create.component.ReviewDateInput
import com.example.presentation.til.create.component.TitleInput
import com.example.presentation.til.create.model.TILCreateState

@Composable
internal fun TILCreateScreenContent(
  createState: TILCreateState,
  modifier: Modifier = Modifier
) {
  Column(
    verticalArrangement = Arrangement.SpaceBetween,
    modifier = modifier
      .imePadding()
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(bottom = 40.dp)
  ) {
    Column {
      HorizontalDivider()
      TitleInput(
        title = createState.title,
        onTitleChange = { createState.title = it },
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 24.dp)
      )
      HorizontalDivider()
      ContentInput(
        content = createState.content,
        onContentChange = { createState.content = it },
        modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight()
          .padding(horizontal = 24.dp)
      )
    }
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
      HorizontalDivider()
      PublishSettingInput(
        isPrivate = createState.isPrivate,
        onIsPrivateChange = { createState.isPrivate = it },
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 24.dp)
      )
      ReviewDateInput(
        reviewDate = createState.reviewDate,
        onReviewDateChange = { createState.reviewDate = it },
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 24.dp)
      )
    }
  }
}

@Preview
@Composable
private fun TILCreateScreenContentPrev() {
  AppTheme {
    Box(modifier = Modifier.background(Color.White)) {
      TILCreateScreenContent(
        createState = remember { TILCreateState() },
        modifier = Modifier
      )
    }
  }
}
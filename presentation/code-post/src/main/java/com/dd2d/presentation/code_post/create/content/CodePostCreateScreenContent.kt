package com.dd2d.presentation.code_post.create.content

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.presentation.code_post.create.model.CodePostFormState

@Composable
internal fun CodePostCreateScreenContent(
  createState: CodePostFormState,
  modifier: Modifier = Modifier
) {
  AnimatedContent(
    targetState = createState.step,
    transitionSpec = {
      when {
        targetState.ordinal > initialState.ordinal -> { // 다음 단계로
          slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
        }

        targetState.ordinal < initialState.ordinal -> { // 이전 단계로
          slideInHorizontally { -it } togetherWith slideOutHorizontally { it }
        }

        else -> EnterTransition.None togetherWith ExitTransition.None
      }
    },
    modifier = modifier
  ) { step ->
    step.Content(
      createState = createState,
      modifier = Modifier
        .fillMaxSize()
    )
  }
}

@Preview
@Preview(locale = "ko", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CodePostCreateScreenContentPrev() {

  val s = rememberCoroutineScope()
  var isLoading by remember { mutableStateOf(false) }
  AppTheme {
    Column(
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.Start,
      modifier = Modifier
        .fillMaxSize()
    ) {
      CodePostCreateScreenContent(
        createState = remember { CodePostFormState() },
        modifier = Modifier
      )
    }
  }
}
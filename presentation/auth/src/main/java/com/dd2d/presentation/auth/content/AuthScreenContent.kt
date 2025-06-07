package com.dd2d.presentation.auth.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.presentation.R
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.image.PainterImage
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.core.presentation_oauth.google.component.GoogleAuthButton
import com.dd2d.core.presentation_oauth.google.model.OAuthButtonDefault
import com.dd2d.core.presentation_oauth.google.model.OAuthResult

@Composable
internal fun AuthScreenContent(
  requestAuth: (OAuthResult) -> Unit,
  modifier: Modifier = Modifier
) {

  var oAuthException by remember { mutableStateOf<ManagedException?>(null) }
  Surface(
    color = MaterialTheme.colorScheme.onBackground,
    modifier = modifier
  ) {
    Column(
      verticalArrangement = Arrangement.spacedBy(12.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 58.dp)
    ) {
      Spacer(Modifier.fillMaxHeight(0.3F))
      PainterImage(res = R.drawable.logo_inverse, modifier = Modifier.align(Alignment.Start))
      GoogleAuthButton(
        onAuthSuccess = requestAuth,
        style = OAuthButtonDefault.googleOAuthButtonStyle(
          text = stringResource(com.dd2d.core.presentation_oauth.R.string.auth_with_google),
          textStyle = TextStyle(fontSize = 16.sp),
          shape = RoundedCornerShape(4.dp)
        )
      )
    }
  }

  oAuthException?.let { e ->
    ErrorDialog(exception = e) {
      oAuthException = null
    }
  }
}

@Preview
@Preview(locale = "ko")
@Composable
private fun AuthScreenContentPrev() {
  AppTheme {
    Column(
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.Start,
      modifier = Modifier
        .fillMaxSize()
    ) {
      AuthScreenContent(
        requestAuth = {},
        modifier = Modifier.fillMaxSize()
      )
    }
  }
}
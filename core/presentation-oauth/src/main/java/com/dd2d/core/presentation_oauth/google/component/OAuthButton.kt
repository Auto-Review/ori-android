package com.dd2d.core.presentation_oauth.google.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.presentation_oauth.google.model.OAuthButtonDefault
import com.dd2d.core.presentation_oauth.google.model.OAuthButtonStyle
import com.dd2d.core.presentation_oauth.google.model.OAuthResult
import com.dd2d.core.presentation_oauth.google.model.OAuthState
import com.dd2d.core.presentation_oauth.google.sdk.OAuthSDK
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun OAuthButton(
  sdk: OAuthSDK,
  style: OAuthButtonStyle,
  onAuthSuccess: (result: OAuthResult) -> Unit,
  modifier: Modifier = Modifier,
  contentPadding: PaddingValues = PaddingValues()
) {
  val state by sdk.state.collectAsStateWithLifecycle()
  var exception by remember { mutableStateOf<ManagedException?>(null) }

  LaunchedEffect(state) {
    exception = (state as? OAuthState.Error)?.exception
    if (state is OAuthState.Success) {
      onAuthSuccess((state as OAuthState.Success).result)
    }
  }

  Surface(
    onClick = sdk::signIn,
    color = style.backgroundColor,
    shape = style.shape,
    modifier = modifier
  ) {
    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .fillMaxWidth()
        .padding(contentPadding)
    ) {
      Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier.weight(0.1F),
        content = { Image(imageVector = style.icon, contentDescription = style.text) },
      )
      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.weight(1F),
        content = { Text(text = style.text, style = style.textStyle) },
      )
      Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier.weight(0.1F),
        content = {
          if (state is OAuthState.Loading) {
            DefaultLoadingIndicator()
          }
        },
      )
    }
  }
}

@Preview
@Composable
private fun OAuthButtonPrev() {
  val scope = rememberCoroutineScope()
  val sdk = object : OAuthSDK() {
    override fun signIn() {
      scope.launch {
        stateToLoading()
        delay(1000)
        stateToSuccess(OAuthResult(""))
      }
    }

    override fun signOut() {}
    override fun withdraw() {}
  }
  OAuthButton(
    sdk = sdk,
    onAuthSuccess = {},
    style = OAuthButtonDefault.googleOAuthButtonStyle(
      textStyle = TextStyle(fontSize = 16.sp),
      shape = RoundedCornerShape(4.dp)
    ),
    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 16.dp),
    modifier = Modifier
  )
}
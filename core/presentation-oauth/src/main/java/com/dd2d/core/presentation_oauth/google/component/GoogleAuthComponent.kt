package com.dd2d.core.presentation_oauth.google.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation_oauth.google.model.OAuthButtonDefault
import com.dd2d.core.presentation_oauth.google.model.OAuthButtonStyle
import com.dd2d.core.presentation_oauth.google.model.OAuthResult
import com.dd2d.core.presentation_oauth.google.sdk.GoogleAuthSDK

@Composable
fun GoogleAuthButton(
    onAuthSuccess: (result: OAuthResult) -> Unit,
    modifier: Modifier = Modifier,
    style: OAuthButtonStyle = OAuthButtonDefault.googleOAuthButtonStyle(),
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 16.dp),
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val sdk = remember { GoogleAuthSDK(context, scope) }

    OAuthButton(
        sdk = sdk,
        style = style,
        onAuthSuccess = onAuthSuccess,
        contentPadding = contentPadding,
        modifier = modifier,
    )
}
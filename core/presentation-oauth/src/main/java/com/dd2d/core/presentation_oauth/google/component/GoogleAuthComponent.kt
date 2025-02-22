package com.dd2d.core.presentation_oauth.google.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.presentation_oauth.R
import com.dd2d.core.presentation_oauth.google.model.OAuthResult
import com.dd2d.core.presentation_oauth.google.model.OAuthState
import com.dd2d.core.presentation_oauth.google.sdk.GoogleAuthSDK
import kotlinx.coroutines.launch

@Composable
fun GoogleAuthComponent(
    onError: (exception: ManagedException) -> Unit,
    onSuccess: (result: OAuthResult) -> Unit,
    modifier: Modifier = Modifier,
    text: @Composable BoxScope.() -> Unit = {
        Text(text = stringResource(R.string.auth_with_google))
    },
    icon: @Composable BoxScope.() -> Unit = {
        Image(imageVector = ImageVector.vectorResource(R.drawable.google), contentDescription = null)
    },
    loadingIndicator: @Composable BoxScope.() -> Unit = {
        DefaultLoadingIndicator()
    },
    shape: Shape = RectangleShape,
    borderStroke: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 16.dp)
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val sdk = remember { GoogleAuthSDK(context) }
    val state by sdk.state.collectAsStateWithLifecycle()

    LaunchedEffect(state) {
        when(state) {
            is OAuthState.Idle -> {}
            is OAuthState.Loading -> {}
            is OAuthState.Error -> {
                onError((state as OAuthState.Error).exception)
                sdk.consumeState()
            }
            is OAuthState.Success -> {
                onSuccess((state as OAuthState.Success).result)
                sdk.consumeState()
            }
        }
    }

    AuthComponent(
        text = text,
        icon = icon,
        loadingIndicator = loadingIndicator,
        isLoading = state is OAuthState.Loading,
        color = Color.White,
        shape = shape,
        borderStroke = borderStroke,
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        scope.launch {
            sdk.oAuth()
        }
    }
}


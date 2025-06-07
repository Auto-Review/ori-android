package com.dd2d.presentation.auth.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.state.UIState
import com.dd2d.presentation.auth.content.AuthScreenContent
import com.dd2d.presentation.auth.view_model.AuthViewModel

@Composable
fun AuthScreen(
  onAuthSuccess: () -> Unit,
  modifier: Modifier = Modifier
) {
  val viewModel = hiltViewModel<AuthViewModel>()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  var exception by remember { mutableStateOf<ManagedException?>(null) }

  LaunchedEffect(key1 = uiState) {
    exception = (uiState as? UIState.Error)?.exception
    if (uiState is UIState.Success) {
      onAuthSuccess()
    }
  }

  Scaffold(
    containerColor = MaterialTheme.colorScheme.onBackground,
    modifier = modifier
  ) { inner ->
    AuthScreenContent(
      requestAuth = viewModel::auth,
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    )
  }

  exception?.let { e ->
    ErrorDialog(exception = e, onConfirm = viewModel::stateToIdle)
  }
}
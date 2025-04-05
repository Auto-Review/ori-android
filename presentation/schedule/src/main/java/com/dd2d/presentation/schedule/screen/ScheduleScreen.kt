package com.dd2d.presentation.schedule.screen

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
import com.dd2d.presentation.schedule.content.ScheduleScreenContent
import com.dd2d.presentation.schedule.view_model.ScheduleViewModel

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<ScheduleViewModel>()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var exception by remember { mutableStateOf<ManagedException?>(null) }

    LaunchedEffect(uiState) {
        exception = (uiState as? UIState.Error)?.exception
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { inner ->
        ScheduleScreenContent(
            scheduleOnMonth = viewModel.schedule,
            modifier = Modifier
                .consumeWindowInsets(inner)
                .fillMaxSize()
                .padding(inner)
        )
    }

    exception?.let { e ->
        ErrorDialog(exception = e) {
            exception = null
        }
    }
}
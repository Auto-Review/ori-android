package com.dd2d.presentation.code_post.create.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.presentation.app_bar.CenterTitleTopBar
import com.dd2d.core.presentation.dialog.CancellableConfirmDialog
import com.dd2d.core.presentation.dialog.ConfirmDialog
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.main_text.Main700Text
import com.dd2d.core.presentation.state.UIState
import com.dd2d.presentation.code_post.create.content.CodePostCreateScreenContent
import com.dd2d.presentation.code_post.create.model.CodePostCreateStep
import com.dd2d.presentation.code_post.create.view_model.CodePostCreateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodePostCreateScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<CodePostCreateViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var exception by remember { mutableStateOf<ManagedException?>(null) }
    var openOnBackConfirmDialog by remember { mutableStateOf(false) }
    var openCreateSuccessDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        exception = (uiState as? UIState.Error)?.exception
        openCreateSuccessDialog = uiState is UIState.Success
    }

    Scaffold(
        topBar = {
            CenterTitleTopBar(
                title = "",
                onBack = { openOnBackConfirmDialog = true },
                actions = {
                    CodePostCreateStepButton(
                        currentStep = viewModel.createState.step,
                        onNextStep = viewModel.createState::nextStep,
                        onPrevStep = viewModel.createState::prevStep,
                        canCreate = viewModel.createState.canCreate,
                        onCreate = viewModel::create,
                        isCreating = uiState is UIState.Loading
                    )
                }
            )
        },
        modifier = modifier
    ) { inner ->
        CodePostCreateScreenContent(
            createState = viewModel.createState,
            modifier = Modifier
                .consumeWindowInsets(inner)
                .fillMaxSize()
                .padding(inner)
        )
    }
    exception?.let { e ->
        ErrorDialog(exception = e, onConfirm = viewModel::stateToIdle)
    }
    if(openOnBackConfirmDialog) {
        CancellableConfirmDialog(
            title = "뒤로가기",
            message = "작성한 내용은 저장되지 않습니다.\n뒤로 가시겠습니까?",
            confirmText = "뒤로 가기",
            onCancel = { openOnBackConfirmDialog = false },
            onConfirm = {
                openOnBackConfirmDialog = false
                onBack()
            }
        )
    }
    if(openCreateSuccessDialog) {
        ConfirmDialog(
            title = "게시물이 생성되었습니다.",
            message = null,
            onConfirm = {
                openCreateSuccessDialog = false
                onBack()
            }
        )
    }
}

@Composable
private fun CodePostCreateStepButton(
    currentStep: CodePostCreateStep,
    onNextStep: () -> Unit,
    onPrevStep: () -> Unit,
    onCreate:() -> Unit,
    isCreating: Boolean,
    canCreate: Boolean,
    modifier: Modifier = Modifier
) {
    val keyboard = LocalSoftwareKeyboardController.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        if(isCreating) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 2.dp,
                trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5F),
                modifier = Modifier.size(24.dp)
            )
        }
        else {
            if(currentStep.ordinal > 0) {
                Main700Text(
                    text = "이전",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 12.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .clickable(onClick = onPrevStep)
                        .padding(10.dp)
                )
            }
            if(currentStep.ordinal in 0..<CodePostCreateStep.entries.lastIndex) {
                Main700Text(
                    text = "다음",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 12.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .clickable(onClick = onNextStep)
                        .padding(10.dp)
                )
            }
            if(currentStep.ordinal == CodePostCreateStep.entries.lastIndex) {
                AnimatedVisibility(
                    visible = canCreate
                ) {
                    Main700Text(
                        text = "완료",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 12.sp,
                        lineHeight = 24.sp,
                        modifier = Modifier
                            .clickable(enabled = canCreate) {
                                keyboard?.hide()
                                onCreate()
                            }
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}

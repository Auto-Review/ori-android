package com.dd2d.ori_android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.dd2d.core.network.NetworkModule
import com.dd2d.core.presentation.dialog.ConfirmDialog

@Composable
internal fun AuthExpireHandler(
    onConfirm: () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var openConfirmDialog by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            NetworkModule.onAuthExpired.collect {
                openConfirmDialog = true
            }
        }
    }

    if(openConfirmDialog) {
        ConfirmDialog(
            title = "로그인이 만료되었습니다.",
            message= null,
            onConfirm = {
                openConfirmDialog = false
                onConfirm()
            },
        )
    }
}
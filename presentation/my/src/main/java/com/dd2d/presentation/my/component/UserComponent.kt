package com.dd2d.presentation.my.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.state.UIState
import com.dd2d.domain.auth_user.user.model.User
import com.dd2d.presentation.my.model.UserUpdateState

@Composable
internal fun UserComponent(
  user: User,
  updateState: UserUpdateState,
  onUpdate: () -> Unit,
  modifier: Modifier = Modifier
) {
  val state by updateState.uiState.collectAsStateWithLifecycle()
  var editMode by remember { mutableStateOf(false) }
  var exception by remember { mutableStateOf<ManagedException?>(null) }

  LaunchedEffect(key1 = state) {
    if (state is UIState.Success) {
      editMode = false
    }
    exception = (state as? UIState.Error)?.exception
  }

  Surface(
    color = MaterialTheme.colorScheme.surface,
    shape = MaterialTheme.shapes.small,
    modifier = modifier
  ) {
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier
        .fillMaxWidth()
    ) {
      Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
          .padding(horizontal = 32.dp, vertical = 20.dp)
      ) {
        UpdatableUserDataField(
          onEditMode = editMode,
          label = "Name",
          originValue = user.nickname,
          updatableValueState = updateState.nameState,
          modifier = Modifier.fillMaxWidth(),
        )
        UserDataField(label = "Email", value = user.email, modifier = Modifier.fillMaxWidth())
      }
      EditButton(
        editMode = editMode,
        onEditModeChange = { on ->
          if (!on) updateState.undoUpdate()
          editMode = on
        },
        canSave = updateState.canSave,
        onSave = onUpdate,
        isSaving = state is UIState.Loading,
        modifier = Modifier.align(Alignment.TopEnd)
      )
    }
  }

  exception?.let { e ->
    ErrorDialog(exception = e, onConfirm = { exception = null })
  }
}



package com.dd2d.presentation.my.model

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd2d.core.core.exception.ClientException
import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.onEachState
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.core.presentation.state.stateToError
import com.dd2d.core.presentation.state.stateToLoading
import com.dd2d.core.presentation.state.stateToSuccess
import com.dd2d.domain.auth_user.user.model.User
import com.dd2d.domain.auth_user.user.model.UserUpdater
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow


internal class UserUpdateState: UIStateManager {
    override val uiState = MutableStateFlow<UIState>(UIState.Idle)

    private var user by mutableStateOf<User?>(null)
    fun initUser(user: User) {
        this.user = user
        nameState.edit {
            replace(start = 0, end = length, text = user.nickname)
        }
    }

    val nameState = TextFieldState(initialText = user?.nickname?: "")

    val canSave by derivedStateOf {
        nameState.text != user?.nickname && nameState.text.isNotBlank()
    }

    fun undoUpdate() {
        nameState.edit {
            replace(start = 0, end = length, text = user?.nickname?: "")
        }
    }

    fun taskFlow(updateFlow: (UserUpdater) -> Flow<DataState<Boolean>>): Flow<DataState<Boolean>> {
        val exception = ClientException.OperationFailException("정보 수정에 실패했습니다.")
        if(user == null) {
            uiState.stateToError(exception)
            return emptyFlow()
        }
        val userUpdater = UserUpdater(
            id = user!!.id,
            nickname = "${nameState.text}",
        )

        return updateFlow(userUpdater).onEachState(
            onLoading = { uiState.stateToLoading() },
            onError = { uiState.stateToError(it) },
            onSuccess = { uiState.stateToSuccess() },
        )
    }
}
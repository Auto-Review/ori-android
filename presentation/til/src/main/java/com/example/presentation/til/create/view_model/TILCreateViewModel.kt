package com.example.presentation.til.create.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.core.state.onEachState
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.domain.til.repository.TILRepository
import com.example.presentation.til.create.model.TILCreateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
internal class TILCreateViewModel @Inject constructor(
    private val tilRepository: TILRepository,
) : ViewModel(), UIStateManager {
    override val uiState = MutableStateFlow<UIState>(UIState.Idle)
    val createState = TILCreateState()

    fun create() {
        tilRepository.createTIL(createState.toTILCreator())
            .onEachState(
                onLoading = { stateToLoading() },
                onError = { stateToError(it) },
                onSuccess = { stateToSuccess() },
            )
            .launchIn(viewModelScope)
    }
}
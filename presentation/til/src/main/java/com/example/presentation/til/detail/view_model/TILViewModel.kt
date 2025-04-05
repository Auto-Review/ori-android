package com.example.presentation.til.detail.view_model


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.core.state.DataState
import com.dd2d.domain.til.repository.TILRepository
import com.example.presentation.til.detail._navigation.TILScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class TILViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    tilRepository: TILRepository
) : ViewModel() {
    private val route = savedStateHandle.toRoute<TILScreenRoute>()

    val tilState = tilRepository
        .getTIL(id = route.id)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DataState.Loading
        )

    fun scrap() {

    }
}
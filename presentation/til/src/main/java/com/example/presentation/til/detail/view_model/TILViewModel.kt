package com.example.presentation.til.detail.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.state.Stateful
import com.dd2d.core.presentation.state.onSuccess
import com.dd2d.core.presentation.state.withStatefulResult
import com.dd2d.domain.auth_user.user.model.User
import com.dd2d.domain.auth_user.user.repository.UserRepository
import com.dd2d.domain.til.model.TIL
import com.dd2d.domain.til.repository.TILRepository
import com.dd2d.domain.til.repository.TILScrapRepository
import com.example.presentation.til.detail._navigation.TILScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class TILViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    userRepository: UserRepository,
    tilRepository: TILRepository,
    private val tilScrapRepository: TILScrapRepository,
) : ViewModel() {
    private val route = savedStateHandle.toRoute<TILScreenRoute>()

    val userState = userRepository.me()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DataState.Loading
        )

    val tilState = tilRepository
        .withStatefulResult { getTIL(id = route.id) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Stateful.Loading
        )

    val isAuthor = combine(
        flow = userState.filterIsInstance<DataState.Success<User>>(),
        flow2 = tilState.filterIsInstance<Stateful.Success<TIL>>(),
        transform = { user, codePost ->
            user.data.id == codePost.data.author.id
        }
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    var isScrapped by mutableStateOf(false)

    fun scrap() {
        tilScrapRepository
            .withStatefulResult { scrap(tilId = route.id) }
            .onSuccess { isScrapped = !isScrapped }
            .launchIn(viewModelScope)
    }
}
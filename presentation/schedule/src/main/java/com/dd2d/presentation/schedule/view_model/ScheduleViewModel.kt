package com.dd2d.presentation.schedule.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.domain.schedule.model.ScheduleOnMonth
import com.dd2d.domain.schedule.model.ScheduleOptions
import com.dd2d.domain.schedule.repository.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
): ViewModel(), UIStateManager {
    override val uiState = MutableStateFlow<UIState>(UIState.Idle)

    private val scheduleOption = MutableStateFlow(ScheduleOptions())
    var schedule by mutableStateOf<ScheduleOnMonth?>(null)
        private set

    fun setScheduleOption(scheduleOptions: ScheduleOptions) {
        scheduleRepository.getSchedule(options = scheduleOptions)
            .onEach { state ->
                when(state) {
                    is DataState.Loading -> stateToLoading()
                    is DataState.Error -> stateToError(state.exception)
                    is DataState.Success -> {
                        schedule = state.data
                        stateToSuccess()
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}

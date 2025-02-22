package com.dd2d.domain.schedule.repository

import com.dd2d.core.core.state.DataState
import com.dd2d.domain.schedule.model.Schedule
import com.dd2d.domain.schedule.model.ScheduleOptions
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getSchedule(options: ScheduleOptions): Flow<DataState<Schedule>>
}
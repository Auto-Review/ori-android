package com.dd2d.data.schedule.repository

import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.asDataState
import com.dd2d.domain.schedule.model.ScheduleOnMonth
import com.dd2d.domain.schedule.model.ScheduleOptions
import com.dd2d.domain.schedule.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(

): ScheduleRepository {
    override fun getSchedule(options: ScheduleOptions): Flow<DataState<ScheduleOnMonth>> = flow {
        emit(ScheduleOnMonth.dummy(size = 30))
    }.asDataState()
}
package com.team3.showbee.data.repository.schedule

import com.team3.showbee.data.entity.*
//import com.team3.showbee.data.entity.Schedule
import com.team3.showbee.data.network.NetworkResponse
import com.team3.showbee.data.network.api.Service
import com.team3.showbee.data.repository.financial.FinancialRepository
import javax.inject.Inject


class ScheduleRepositoryImpl @Inject constructor(
    private val service: Service
) : ScheduleRepository {
    override suspend fun createSchedule(schedule: Schedule): NetworkResponse<Int, ErrorResponse> {
        return service.createScheduleResponse(schedule)
    }
    override suspend fun inviteUser(email: String): NetworkResponse<InviteeResponse, ErrorResponse> {
        return service.inviteUserEmailResponse(email)
    }
}
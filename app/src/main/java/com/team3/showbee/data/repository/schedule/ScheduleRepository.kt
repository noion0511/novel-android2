package com.team3.showbee.data.repository.schedule

import com.team3.showbee.data.entity.*
//import com.team3.showbee.data.entity.Schedule
import com.team3.showbee.data.network.NetworkResponse

interface ScheduleRepository {
    suspend fun inviteUser(email: String) : NetworkResponse<InviteeResponse, ErrorResponse>
    suspend fun createSchedule(schedule: Schedule) : NetworkResponse<Int, ErrorResponse>
}
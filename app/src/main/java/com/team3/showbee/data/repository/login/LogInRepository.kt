package com.team3.showbee.data.repository.login

import com.team3.showbee.data.entity.BaseResponse
import com.team3.showbee.data.entity.ErrorResponse
import com.team3.showbee.data.network.NetworkResponse
import retrofit2.Call

interface LogInRepository {
    suspend fun signIn(email: String, password: String): NetworkResponse<BaseResponse, ErrorResponse>
}
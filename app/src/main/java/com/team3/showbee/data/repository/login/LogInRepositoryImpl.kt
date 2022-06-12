package com.team3.showbee.data.repository.login

import com.team3.showbee.data.entity.BaseResponse
import com.team3.showbee.data.entity.ErrorResponse
import com.team3.showbee.data.network.NetworkResponse
import com.team3.showbee.data.network.api.Service
import retrofit2.Call
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(
    private val service: Service
) : LogInRepository {
    override suspend fun signIn(email: String, password: String): NetworkResponse<BaseResponse, ErrorResponse> {
        return service.signInResponse(email, password)
    }
}
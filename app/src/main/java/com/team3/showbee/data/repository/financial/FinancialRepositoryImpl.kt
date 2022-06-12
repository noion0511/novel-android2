package com.team3.showbee.data.repository.financial

import com.team3.showbee.data.entity.*
import com.team3.showbee.data.network.NetworkResponse
import com.team3.showbee.data.network.api.Service
import javax.inject.Inject

class FinancialRepositoryImpl @Inject constructor(
    private val service: Service
) : FinancialRepository {
    override suspend fun createFinancial(financial: Financial): NetworkResponse<Int, ErrorResponse> {
        return service.createFinancialResponse(financial)
    }

    override suspend fun getFinancial(fid: Long): NetworkResponse<Financial, ErrorResponse> {
        return service.getFinancialResponse(fid)
    }

    override suspend fun getMonthly(nowDate: String): NetworkResponse<Map<String, List<Long>>, ErrorResponse> {
        return service.getMonthlyResponse(nowDate)
    }

    override suspend fun getList(nowDate: String): NetworkResponse<MutableMap<String, MutableList<FinancialContentModel>>, ErrorResponse> {
        return service.getListResponse(nowDate)
    }

    override suspend fun getMonthlyTotal(nowDate: String): NetworkResponse<List<Long>, ErrorResponse> {
        return service.getMonthlyTotalResponse(nowDate)
    }

    override suspend fun updateFinancial(financial : Financial): NetworkResponse<BaseResponse, ErrorResponse> {
        return service.updateFinancialResponse(financial)
    }

    override suspend fun deleteFinancial(fid: Long): NetworkResponse<BaseResponse, ErrorResponse> {
        return service.deleteFinancialResponse(fid)
    }
}
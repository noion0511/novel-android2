package com.team3.showbee.data.repository.financial

import com.team3.showbee.data.entity.*
import com.team3.showbee.data.network.NetworkResponse

interface FinancialRepository {
    suspend fun createFinancial(financial : Financial) : NetworkResponse<Int, ErrorResponse>
    suspend fun getFinancial(fid: Long) : NetworkResponse<Financial, ErrorResponse>
    suspend fun getMonthly(nowDate: String) : NetworkResponse<Map<String,List<Long>>, ErrorResponse>
    suspend fun getList(nowDate: String) : NetworkResponse<MutableMap<String, MutableList<FinancialContentModel>>, ErrorResponse>
    suspend fun getMonthlyTotal(nowDate: String) : NetworkResponse<List<Long>, ErrorResponse>
    suspend fun updateFinancial(financial : Financial) : NetworkResponse<BaseResponse, ErrorResponse>
    suspend fun deleteFinancial(fid: Long) : NetworkResponse<BaseResponse, ErrorResponse>
}
package com.team3.showbee.data.entity

data class BaseResponse(
    val success: Boolean = false,
    val code: Int = -1,
    val msg: String = "",
    val data: Any?
)

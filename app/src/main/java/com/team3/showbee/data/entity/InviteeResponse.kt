package com.team3.showbee.data.entity

import com.google.gson.annotations.SerializedName

data class InviteeResponse(
    val success: Boolean = false,
    val code: Int = -1,
    val msg: String = "",
    val data: InviteeModel
)

data class InviteeModel(
    @SerializedName("email")
    val email:String,
    @SerializedName("name")
    val name:String
)

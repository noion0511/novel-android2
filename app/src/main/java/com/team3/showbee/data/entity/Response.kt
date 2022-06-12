package com.team3.showbee.data.entity

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("body") val body: Body
)
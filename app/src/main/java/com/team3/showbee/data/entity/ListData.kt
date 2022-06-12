package com.team3.showbee.data.entity

import com.google.gson.annotations.SerializedName

data class ListData(
    @SerializedName("projectId") val projectId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: String,
    @SerializedName("photoUrl") val photoUrl: String,
    @SerializedName("makerName") val makerName: String,
    @SerializedName("additionalInfo") val additionalInfo: String,
    @SerializedName("makerPage") val makerPage: String,
    @SerializedName("category") val category: Category,
    @SerializedName("price") val price: Int = 0,
    @SerializedName("targetAmount") val targetAmount: Int = 0,
    @SerializedName("landingUrl") val landingUrl: String
)
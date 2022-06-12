package com.team3.showbee.data.entity

data class Schedule(
    val stitle: String = "",
    val content: String = "",
    val price: Int = 0,
    val date: String = "",
    val cycle: Int = 0,
    val shared: Boolean = false,
    val participant: ArrayList<String>,
    val inoutcome: Boolean = true,
    val category: String = ""
)

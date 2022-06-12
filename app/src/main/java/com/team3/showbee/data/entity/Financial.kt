package com.team3.showbee.data.entity

data class Financial(
    val fid: Long? = null,
    val date: String = "",
    val content: String? = null,
    val price: String = "",
    val category: String? = null,
    val bank: String? = null,
    val memo: String? = null,
    val inoutcome: Boolean = true
)

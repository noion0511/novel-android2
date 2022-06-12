package com.team3.showbee.data.entity

data class Board(
    val id: Long = 0L,
    val title: String = "",
    val content: String = "",
    val writer: String = "",
    val totalHits: Int = 0,
    val createdDate: String = "",
    val modifiedDate: String = ""
)

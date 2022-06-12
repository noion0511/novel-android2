package com.team3.showbee.data.entity

data class Post(
    val pId: Long = 0L,
    val boardId: Long = 0L,
    val title: String = "",
    val content: String = "",
    val hits: Int = 0,
    val createdDate: String = "",
    val modifiedDate: String = ""
)
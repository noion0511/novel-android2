package com.team3.showbee.data.repository.user

import com.team3.showbee.data.entity.BaseResponse
import com.team3.showbee.data.entity.Board
import com.team3.showbee.data.entity.ErrorResponse
import com.team3.showbee.data.entity.Post
import com.team3.showbee.data.network.NetworkResponse

interface UserRepository {
    suspend fun setBoard(boardId: Long): NetworkResponse<Board, ErrorResponse>
    suspend fun setBoardList(): NetworkResponse<List<Board>, ErrorResponse>
    suspend fun setPostList(boardId: Long): NetworkResponse<List<Post>, ErrorResponse>
    suspend fun signUp(email:String, password: String, username: String): NetworkResponse<BaseResponse, ErrorResponse>
    suspend fun deleteUser(): NetworkResponse<BaseResponse, ErrorResponse>
    suspend fun emailCheck(email:String): NetworkResponse<Boolean, ErrorResponse>
    suspend fun updateUsername(username:String): NetworkResponse<Boolean, ErrorResponse>
    suspend fun updatePassword(password:String): NetworkResponse<Boolean, ErrorResponse>
}
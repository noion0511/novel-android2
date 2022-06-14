package com.team3.showbee.data.repository.user

import com.team3.showbee.data.entity.*
import com.team3.showbee.data.network.NetworkResponse

interface UserRepository {
    suspend fun setBoard(boardId: Long): NetworkResponse<Board, ErrorResponse>
    suspend fun deleteBoard(boardId: Long): NetworkResponse<Boolean, ErrorResponse>
    suspend fun setBoardList(): NetworkResponse<List<Board>, ErrorResponse>
    suspend fun setPostList(boardId: Long): NetworkResponse<List<Post>, ErrorResponse>
    suspend fun setPost(postId: Long): NetworkResponse<Post, ErrorResponse>
    suspend fun deletePost(postId: Long): NetworkResponse<Boolean, ErrorResponse>
    suspend fun createPost(post : PostRequestDto): NetworkResponse<Int, ErrorResponse>
    suspend fun updatePost(postId : Long, postMap: HashMap<String,String>): NetworkResponse<Int, ErrorResponse>
    suspend fun signUp(email:String, password: String, username: String): NetworkResponse<BaseResponse, ErrorResponse>
    suspend fun deleteUser(): NetworkResponse<BaseResponse, ErrorResponse>
    suspend fun emailCheck(email:String): NetworkResponse<Boolean, ErrorResponse>
    suspend fun updateUsername(username:String): NetworkResponse<Boolean, ErrorResponse>
    suspend fun updatePassword(password:String): NetworkResponse<Boolean, ErrorResponse>
}
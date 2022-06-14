package com.team3.showbee.data.repository.user

import com.team3.showbee.data.entity.*
import com.team3.showbee.data.network.NetworkResponse
import com.team3.showbee.data.network.api.Service
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: Service
) : UserRepository {
    override suspend fun setBoard(boardId: Long): NetworkResponse<Board, ErrorResponse> {
        return apiService.setBoardResponse(boardId)
    }

    override suspend fun deleteBoard(boardId: Long): NetworkResponse<Boolean, ErrorResponse> {
        return apiService.deleteBoardResponse(boardId)
    }

    override suspend fun setBoardList(): NetworkResponse<List<Board>, ErrorResponse> {
        return apiService.setBoardListResponse()
    }

    override suspend fun setPostList(boardId: Long): NetworkResponse<List<Post>, ErrorResponse> {
        return apiService.setPostListResponse(boardId)
    }

    override suspend fun setPost(postId: Long): NetworkResponse<Post, ErrorResponse> {
        return apiService.setPostResponse(postId)
    }

    override suspend fun deletePost(postId: Long): NetworkResponse<Boolean, ErrorResponse> {
        return apiService.deletePostResponse(postId)
    }

    override suspend fun createPost(post: PostRequestDto): NetworkResponse<Int, ErrorResponse> {
        return apiService.createPostResponse(post)
    }

    override suspend fun updatePost(postId: Long, postMap: HashMap<String,String>): NetworkResponse<Int, ErrorResponse> {
        return apiService.updatePostResponse(postId, postMap)
    }

    override suspend fun signUp(
        email: String,
        password: String,
        username: String
    ): NetworkResponse<BaseResponse, ErrorResponse> {
        return apiService.signUpResponse(email, password, username)
    }

    override suspend fun deleteUser(): NetworkResponse<BaseResponse, ErrorResponse> {
        return apiService.deleteUserResponse()
    }

    override suspend fun emailCheck(email: String): NetworkResponse<Boolean, ErrorResponse> {
        return apiService.emailCheckResponse(email)
    }

    override suspend fun updateUsername(username: String): NetworkResponse<Boolean, ErrorResponse> {
        return apiService.updateUsernameResponse(username)
    }

    override suspend fun updatePassword(password: String): NetworkResponse<Boolean, ErrorResponse> {
        return apiService.updatePasswordResponse(password)
    }
}
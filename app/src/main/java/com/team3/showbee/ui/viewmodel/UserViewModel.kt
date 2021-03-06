package com.team3.showbee.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.team3.showbee.data.entity.*
import com.team3.showbee.data.network.NetworkResponse
import com.team3.showbee.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    val repository: UserRepository
) : ViewModel() {

    private val _msg = MutableLiveData<Event<String>>()
    val msg : LiveData<Event<String>> = _msg

    private val _bool = MutableLiveData<Event<Boolean>>()
    val bool : LiveData<Event<Boolean>> = _bool

    private val _token = MutableLiveData<Token>()
    val token : LiveData<Token> = _token

    private val _info = MutableLiveData<Event<String>>()
    val info : LiveData<Event<String>> = _info

    private val _boardList = MutableLiveData<Event<List<Board>>>()
    val boardList : LiveData<Event<List<Board>>> = _boardList

    private val _board = MutableLiveData<Event<Board>>()
    val board : LiveData<Event<Board>> = _board

    private val _postList = MutableLiveData<Event<List<Post>>>()
    val postList : LiveData<Event<List<Post>>> = _postList

    private val _post = MutableLiveData<Event<Post>>()
    val post : LiveData<Event<Post>> = _post

    fun setBoardList() {
        viewModelScope.launch {
            val response = repository.setBoardList()
            val type = "노벨"

            when(response) {
                is NetworkResponse.Success -> {
                    _boardList.postValue(Event(response.body))
                }
                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type)
                }
                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type)
                }
                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type)
                }
            }
        }
    }

    fun setBoard(boardId: Long) {
        viewModelScope.launch {
            val response = repository.setBoard(boardId = boardId)
            val type = "one board"

            when(response) {
                is NetworkResponse.Success -> {
                    _board.postValue(Event(response.body))
                }
                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type)
                }
                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type)
                }
                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type)
                }
            }
        }
    }

    fun deleteBoard(boardId: Long) {
        viewModelScope.launch {
            val response = repository.deleteBoard(boardId = boardId)
            val type = "one board"

            when(response) {
                is NetworkResponse.Success -> {
                    _bool.postValue(Event(response.body))
                }
                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type)
                }
                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type)
                }
                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type)
                }
            }
        }
    }

    fun setPostList(boardId: Long) {
        viewModelScope.launch {
            val response = repository.setPostList(boardId)
            val type = "노벨"

            when(response) {
                is NetworkResponse.Success -> {
                    _postList.postValue(Event(response.body))
                }
                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type)
                }
                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type)
                }
                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type)
                }
            }
        }
    }

    fun setPost(postId: Long) {
        viewModelScope.launch {
            val response = repository.setPost(postId)
            val type = "노벨"

            when(response) {
                is NetworkResponse.Success -> {
                    _post.postValue(Event(response.body))
                }
                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type)
                }
                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type)
                }
                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type)
                }
            }
        }
    }

    fun createPost(post: PostRequestDto) {
        viewModelScope.launch {
            val response = repository.createPost(post)
            val type = "노벨"

            when(response) {
                is NetworkResponse.Success -> {
                    _msg.postValue(Event(response.body.toString()))
                }
                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type)
                }
                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type)
                }
                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type)
                }
            }
        }
    }

    fun updatePost(title:String, content: String, postId: Long) {
        viewModelScope.launch {
            val postMap = HashMap<String, String>()
            postMap["title"] = title
            postMap["content"] = content

            val response = repository.updatePost(postId, postMap)
            val type = "노벨"

            when(response) {
                is NetworkResponse.Success -> {
                    _msg.postValue(Event(response.body.toString()))
                }
                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type)
                }
                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type)
                }
                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type)
                }
            }
        }
    }

    fun deletePost(pId: Long) {
        viewModelScope.launch {
            val response = repository.deletePost(postId = pId)
            val type = "one board"

            when(response) {
                is NetworkResponse.Success -> {
                    _bool.postValue(Event(response.body))
                }
                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type)
                }
                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type)
                }
                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type)
                }
            }
        }
    }

    fun signup(email: String, username: String, password: String) {
        if(validation(email, username, password)) {
            viewModelScope.launch {
                val response = repository.signUp(email, username, password)
                val type = "회원가입을"

                when(response) {
                    is NetworkResponse.Success -> {
                        _msg.postValue(Event(response.body.msg))
                    }
                    is NetworkResponse.ApiError -> {
                        postValueEvent(0, type)
                    }
                    is NetworkResponse.NetworkError -> {
                        postValueEvent(1, type)
                    }
                    is NetworkResponse.UnknownError -> {
                        postValueEvent(2, type)
                    }
                }
            }
        }
    }

    fun deleteUser() {
        viewModelScope.launch {
            val response = repository.deleteUser()
            val type = "회원탈퇴를"

            when(response) {
                is NetworkResponse.Success -> {
                    _msg.postValue(Event(response.body.msg))
                }
                is NetworkResponse.ApiError -> {
                    postValueEvent(0, type)
                }
                is NetworkResponse.NetworkError -> {
                    postValueEvent(1, type)
                }
                is NetworkResponse.UnknownError -> {
                    postValueEvent(2, type)
                }
            }
        }
    }

    fun updateUsername(username: String) {
        if (username.isNotEmpty()) {
            viewModelScope.launch {
                val response = repository.updateUsername(username)
                val type = "닉네임 변경을"

                when(response) {
                    is NetworkResponse.Success -> {
                        _info.postValue(Event(response.body.toString()))
                    }
                    is NetworkResponse.ApiError -> {
                        postValueEvent(0, type)
                    }
                    is NetworkResponse.NetworkError -> {
                        postValueEvent(1, type)
                    }
                    is NetworkResponse.UnknownError -> {
                        postValueEvent(2, type)
                    }
                }
            }
        }
    }

    fun updatePassword(password: String) {
        if (password.isNotEmpty()) {
            viewModelScope.launch {
                val response = repository.updatePassword(password)
                val type = "비밀번호 변경을"

                when(response) {
                    is NetworkResponse.Success -> {
                        _info.postValue(Event(response.body.toString()))
                    }
                    is NetworkResponse.ApiError -> {
                        postValueEvent(0, type)
                    }
                    is NetworkResponse.NetworkError -> {
                        postValueEvent(1, type)
                    }
                    is NetworkResponse.UnknownError -> {
                        postValueEvent(2, type)
                    }
                }
            }
        }
    }

    fun checkEmail(email: String) {
        if (email.isNotEmpty()) {
            viewModelScope.launch {
                val response = repository.emailCheck(email)
                val type = "이메일 조회를"

                when(response) {
                    is NetworkResponse.Success -> {
                        _bool.postValue(Event(response.body))
                    }
                    is NetworkResponse.ApiError -> {
                        postValueEvent(0, type)
                    }
                    is NetworkResponse.NetworkError -> {
                        postValueEvent(1, type)
                    }
                    is NetworkResponse.UnknownError -> {
                        postValueEvent(2, type)
                    }
                }
            }
        }
    }

    private fun validation(email: String, username : String, password: String): Boolean {
        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            _msg.postValue(Event("아이디와 비밀번호를 입력해주세요!"))
            return false
        }
        return true
    }

    private fun postValueEvent(value : Int, type: String) {
        val msgArrayList = arrayOf("Api 오류 : $type 실패했습니다.",
            "서버 오류 : $type 실패했습니다.",
            "알 수 없는 오류 : $type 실패했습니다."
        )

        when(value) {
            0 -> _msg.postValue(Event(msgArrayList[0]))
            1 -> _msg.postValue(Event(msgArrayList[1]))
            2 -> _msg.postValue(Event(msgArrayList[2]))
        }
    }
}
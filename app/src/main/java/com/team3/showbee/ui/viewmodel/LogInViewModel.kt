package com.team3.showbee.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team3.showbee.data.entity.Event
import com.team3.showbee.data.entity.Token
import com.team3.showbee.data.network.NetworkResponse
import com.team3.showbee.data.repository.login.LogInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    val repository: LogInRepository
): ViewModel() {

    private val _msg = MutableLiveData<Event<String>>()
    val errorMsg : LiveData<Event<String>> = _msg

    private val _token = MutableLiveData<Token>()
    val token : LiveData<Token> = _token

    fun login(email: String, password: String) {
        if(validation(email, password)) {

            viewModelScope.launch {
                val response = repository.signIn(email, password)
                val type = "로그인을"

                when(response) {
                    is NetworkResponse.Success -> {
                        _token.postValue(Token(response.body.data!!.toString()))
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

    private fun validation(username : String, password: String): Boolean {
        if (username.isEmpty() || password.isEmpty()) {
            _msg.postValue(Event("아이디와 비밀번호를 입력해주세요!"))
            return false
        }
        return true
    }

    private fun postValueEvent(value : Int, type: String) {
        val msgArrayList = arrayOf("Api 오류 : $type 실패했습니다.",
            "서버 오류 : $type 실패했습니다.",
            "존재하지 않는 사용자 : $type 실패했습니다."
        )

        when(value) {
            0 -> _msg.postValue(Event(msgArrayList[0]))
            1 -> _msg.postValue(Event(msgArrayList[1]))
            2 -> _msg.postValue(Event(msgArrayList[2]))
        }
    }
}
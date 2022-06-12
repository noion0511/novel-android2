package com.team3.showbee.ui.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import android.util.LogPrinter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team3.showbee.data.entity.*
import com.team3.showbee.data.network.NetworkResponse
import com.team3.showbee.data.repository.schedule.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    val repository: ScheduleRepository
) : ViewModel() {
    private val _msg = MutableLiveData<Event<String>>()
    private val _email = MutableLiveData<String>()
    val msg : LiveData<Event<String>> = _msg
    val email:LiveData<String>
        get() = _email

    init {
        _email.value = ""
    }
    fun inviteUser(email: String) {
        if(validation(email)) {
            viewModelScope.launch {
                val email = email
                val response:NetworkResponse<InviteeResponse,ErrorResponse> = repository.inviteUser(email)
                when(response) {
                    is NetworkResponse.Success -> {
                        Log.d("dkdkd22", "djfljwife")
                        _msg.value = (Event(response.body.msg))
                        val message = response.body.msg
                        val inviteeEmail = response.body.data.email
                        Log.d("TAG", "inviteUser: ${_msg.value}")
                        Log.d("dkdkd33", "${inviteeEmail}")
                        _email.value = inviteeEmail
                        existUser(message, inviteeEmail)
                        Log.d(TAG, "inviteUser: exist user 실행??")
                    }
                    is NetworkResponse.ApiError -> {
                        postValueEvent(0)
                    }
                    is NetworkResponse.NetworkError -> {
                        postValueEvent(1)
                    }
                    is NetworkResponse.UnknownError -> {
                        postValueEvent(2)
                    }
                }
            }
        }
    }
    fun createS(stitle:String, content:String, price:Int, date:String, cycle:Int, shared:Boolean, participant:ArrayList<String>, inoutcome:Boolean,category:String) {
        viewModelScope.launch {
            val schedule = Schedule(stitle, content, price, date, cycle, shared, participant, inoutcome, category)
            val response:NetworkResponse<Int, ErrorResponse> = repository.createSchedule(schedule)

            when(response) {
                is NetworkResponse.Success -> {
                    _msg.postValue(Event(response.body.toString()))
                }
                is NetworkResponse.ApiError -> {
                    postValueEvent(0)
                }
                is NetworkResponse.NetworkError -> {
                    postValueEvent(1)
                }
                is NetworkResponse.UnknownError -> {
                    postValueEvent(2)
                }
            }
        }
    }

    fun existUser(result:String, email: String) {
        Log.d(TAG, "existUser: 첫 부분")
        if(result == "성공하였습니다.") {
            Log.d(TAG, "existUser: ${result}")

            val email = email

            inviteeList.add(Pair(email, "sdfksjf"))
            Log.d("텍스트띄우기", "onCreate: ${inviteeList.size} + ${inviteeList[0].first} + ${inviteeList[0].second}")
            Log.d(TAG, "existUser: ${inviteeList}")
        }
    }


    private fun validation(email: String): Boolean {
        if (email.isEmpty()) {
            _msg.postValue(Event("이메일을 입력해주세요"))
            return false
        }
        return true
    }

    private fun postValueEvent(value : Int) {
        val msgArrayList = arrayOf("Api 오류 : 실패했습니다.",
            "서버 오류 : 실패했습니다.",
            "존재하지 않는 사용자입니다."
        )

        when(value) {
            0 -> _msg.postValue(Event(msgArrayList[0]))
            1 -> _msg.postValue(Event(msgArrayList[1]))
            2 -> _msg.postValue(Event(msgArrayList[2]))
        }
    }
}


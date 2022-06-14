package com.team3.showbee.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team3.showbee.data.entity.Event
import com.team3.showbee.data.entity.Financial
import com.team3.showbee.data.entity.FinancialContentModel
import com.team3.showbee.data.entity.FinancialListModel
import com.team3.showbee.data.network.NetworkResponse
import com.team3.showbee.data.repository.financial.FinancialRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinancialViewModel @Inject constructor(
    val repository: FinancialRepository
) : ViewModel() {
    private val _msg = MutableLiveData<Event<String>>()
    val msg : LiveData<Event<String>> = _msg

    private val _list = MutableLiveData<Event<MutableMap<String, MutableList<FinancialContentModel>>>>()
    val list : LiveData<Event<MutableMap<String, MutableList<FinancialContentModel>>>> = _list

    private val _calenderList = MutableLiveData<Event<Map<String,List<Long>>>>()
    val calendarList : LiveData<Event<Map<String,List<Long>>>> = _calenderList

    private val _total = MutableLiveData<Event<List<Long>>>()
    val total : LiveData<Event<List<Long>>> = _total

    private val _financial = MutableLiveData<Event<Financial>>()
    val financial : LiveData<Event<Financial>> = _financial

    fun create(title: String, content: String, writer: String) {
        viewModelScope.launch {
            val financial = Financial(title = title, content = content, writer = writer)
            val response = repository.createFinancial(financial)

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

    fun getFinancial(fid: Long) {
        viewModelScope.launch {
            val response = repository.getFinancial(fid)

            when(response) {
                is NetworkResponse.Success -> {
                    _financial.postValue(Event(response.body))
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

    fun getMonthlyTotal(nowDate:String) {
        viewModelScope.launch {
            val response = repository.getMonthlyTotal(nowDate = nowDate)

            when(response) {
                is NetworkResponse.Success -> {
                    _total.postValue(Event(response.body))
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

    fun getMonthly(nowDate:String) {
        viewModelScope.launch {
            val response = repository.getMonthly(nowDate = nowDate)

            when(response) {
                is NetworkResponse.Success -> {
                    _calenderList.postValue(Event(response.body))
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

    fun getList(nowDate:String) {
        viewModelScope.launch {
            val response = repository.getList(nowDate = nowDate)

            when(response) {
                is NetworkResponse.Success -> {
                    _list.postValue(Event(response.body))
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

//    fun update(fid: Long, date: String, content: String, price: String, category: String, bank: String, memo: String, inoutcome: Boolean) {
//        viewModelScope.launch {
//            val financial = Financial(fid = fid, date = date, content = content, price = price, category = category, bank = bank, memo = memo, inoutcome = inoutcome)
//            val response = repository.createFinancial(financial)
//
//            when(response) {
//                is NetworkResponse.Success -> {
//                    _msg.postValue(Event(response.body.toString()))
//                }
//                is NetworkResponse.ApiError -> {
//                    postValueEvent(0)
//                }
//                is NetworkResponse.NetworkError -> {
//                    postValueEvent(1)
//                }
//                is NetworkResponse.UnknownError -> {
//                    postValueEvent(2)
//                }
//            }
//        }
//    }

    fun delete(fid: Long) {
        viewModelScope.launch {
            val response = repository.deleteFinancial(fid)

            when(response) {
                is NetworkResponse.Success -> {
                    _msg.postValue(Event(response.body.msg))
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

    private fun postValueEvent(value : Int) {
        val msgArrayList = arrayOf("Api 오류 : 실패했습니다.",
            "서버 오류 : 실패했습니다.",
            "알 수 없는 오류 : 실패했습니다."
        )

        when(value) {
            0 -> _msg.postValue(Event(msgArrayList[0]))
            1 -> _msg.postValue(Event(msgArrayList[1]))
            2 -> _msg.postValue(Event(msgArrayList[2]))
        }
    }
}
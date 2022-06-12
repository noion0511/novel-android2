package com.team3.showbee.ui.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.team3.showbee.R
import com.team3.showbee.SharedPref
import com.team3.showbee.databinding.ActivityAddFinancialBinding
import com.team3.showbee.databinding.ActivityAddIncomeExpenditureBinding
import com.team3.showbee.databinding.ActivityMainBinding
import com.team3.showbee.ui.viewmodel.FinancialViewModel
import com.team3.showbee.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class GetFinancialActivity : AppCompatActivity() {
    private var _binding: ActivityAddFinancialBinding? = null
    private val binding: ActivityAddFinancialBinding get() = requireNotNull(_binding)
    private lateinit var viewModel: FinancialViewModel

    var thisYear =""
    var thisMonth = ""
    var thisDay = ""
    var category = ""
    var resultDay = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddFinancialBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(FinancialViewModel::class.java)
        setContentView(binding.root)

        initView()
        observeData()
    }

    private fun initView() {
        binding.choiceIncomeExpense.setOnCheckedChangeListener{group, checkedId ->
            when (checkedId) {
                R.id.radioButton -> {
                    binding.radioButton.setTextColor(Color.parseColor("#FF8B00"))
                    binding.radioButton2.setTextColor(Color.parseColor("#989898"))
                    category = "income"

                }
                R.id.radioButton2 -> {
                    binding.radioButton.setTextColor(Color.parseColor("#989898"))
                    binding.radioButton2.setTextColor(Color.parseColor("#FF8B00"))
                    category = "expense"
                }
            }
        }
        binding.editTextDay.setOnClickListener {
            setCalenderDay()
        }
        binding.save.setOnClickListener {
        }
    }

    private fun setCalenderDay() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dateListener = object : DatePickerDialog.OnDateSetListener {
            @SuppressLint("SetTextI18n")
            override fun onDateSet(
                view: DatePicker?,
                yearDate: Int,
                monthDate: Int,
                dayOfMonth: Int
            ) {
                binding.editTextDay.text = "${yearDate}년 ${monthDate+1} 월 ${dayOfMonth}일"
                thisMonth = "${monthDate+1}"
                thisDay = "$dayOfMonth"

                if(thisMonth.length != 2){
                    thisMonth = "0$thisMonth"
                }

                if(thisDay.length != 2){
                    thisDay = "0$thisDay"
                }
                thisYear = "$yearDate"
                resultDay = "$thisYear-$thisMonth-$thisDay"
            }
        }

        val datePicker = DatePickerDialog(this, dateListener, year, month, day)
        datePicker.show()
    }

    private fun observeData() {
        with(viewModel) {
            msg.observe(this@GetFinancialActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    if (it == "true") {
                        Toast.makeText(this@GetFinancialActivity, "성공했습니다.", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this@GetFinancialActivity, "실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
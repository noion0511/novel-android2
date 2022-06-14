package com.team3.showbee.ui.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.team3.showbee.R
import com.team3.showbee.databinding.ActivityAddFinancialBinding
import com.team3.showbee.ui.viewmodel.FinancialViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddFinancialActivity : AppCompatActivity() {
    private var _binding: ActivityAddFinancialBinding? = null
    private val binding: ActivityAddFinancialBinding get() = requireNotNull(_binding)
    private lateinit var viewModel: FinancialViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddFinancialBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(FinancialViewModel::class.java)
        setContentView(binding.root)

        initView()
        observeData()
    }

    private fun initView() {
        binding.save.setOnClickListener {
            viewModel.create(title = binding.editTextTitle.text.toString(), content = binding.editTextContent.text.toString(), writer = "작성자1")
        }
    }

    private fun observeData() {
        with(viewModel) {
            msg.observe(this@AddFinancialActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    Toast.makeText(this@AddFinancialActivity, "성공했습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
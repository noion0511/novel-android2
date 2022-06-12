package com.team3.showbee.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.team3.showbee.R
import com.team3.showbee.databinding.ActivityUpdateUserinfoBinding
import com.team3.showbee.databinding.ActivityUserAccountBinding
import com.team3.showbee.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateUserInfoActivity : AppCompatActivity() {
    private var _binding: ActivityUpdateUserinfoBinding? = null
    private val binding: ActivityUpdateUserinfoBinding get() = requireNotNull(_binding)
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUpdateUserinfoBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(binding.root)

        initView()
        observeData()
    }

    private fun initView() {
        binding.btnUpdatePassword.setOnClickListener {
            try {
                val password = binding.etvPassword.text.toString()
                val passwordCheck = binding.etvPasswordCheck.text.toString()
                if (password == passwordCheck) {
                    viewModel.updatePassword(password)
                }
                else {
                    binding.etvPasswordCheck.error = "비밀번호 확인이 같지 않습니다."
                }
            }
            finally {
                binding.etvPassword.setText("")
                binding.etvPasswordCheck.setText("")
            }

        }
        binding.btnUpdateUsername.setOnClickListener {
            try {
                val username = binding.etvUsername.text.toString()
                viewModel.updateUsername(username)
            }
            finally {
                binding.etvUsername.setText("")
            }
        }
    }

    private fun observeData() {
        with(viewModel) {
            info.observe(this@UpdateUserInfoActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    Toast.makeText(this@UpdateUserInfoActivity, "변경되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
package com.team3.showbee.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.team3.showbee.R
import com.team3.showbee.SharedPref
import com.team3.showbee.databinding.ActivityLogInBinding
import com.team3.showbee.databinding.ActivitySignUpBinding
import com.team3.showbee.ui.viewmodel.LogInViewModel
import com.team3.showbee.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private var _binding: ActivitySignUpBinding? = null
    private val binding: ActivitySignUpBinding get() = requireNotNull(_binding)
    private lateinit var viewModel: UserViewModel

    var isEmailChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(binding.root)

        initView()
        observeData()
    }

    private fun initView() {
        binding.emailCheckBtn.setOnClickListener {
            val userEmail = binding.userIdSignUp.text.toString()

            viewModel.checkEmail(userEmail)
        }

        binding.signUp.setOnClickListener {
            val userEmail = binding.userIdSignUp.text.toString()
            val userName = binding.userNickname.text.toString()
            val userPw = binding.userPwSignUp.text.toString()
            val userPwCheck = binding.userPwCheck.text.toString()

            if (userPw == userPwCheck) {
                viewModel.signup(userEmail, userName, userPw)
            }
        }
    }

    private fun observeData() {
        with(viewModel) {
            msg.observe(this@SignUpActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    Toast.makeText(this@SignUpActivity, it, Toast.LENGTH_SHORT).show()
                    if (it == "성공하였습니다.") {
                        val intent = Intent(this@SignUpActivity, LogInActivity::class.java)
                        startActivity(intent)
                    }
                }
            }

            emailCheck.observe(this@SignUpActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    if (it) {
                        Toast.makeText(this@SignUpActivity, "이미 사용중인 이메일입니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@SignUpActivity, "사용 가능한 이메일입니다.", Toast.LENGTH_SHORT).show()
                        isEmailChecked = true
                    }
                }
            }
        }
    }
}
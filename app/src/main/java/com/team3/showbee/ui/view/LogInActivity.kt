package com.team3.showbee.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.team3.showbee.SharedPref
import com.team3.showbee.databinding.ActivityLogInBinding
import com.team3.showbee.ui.viewmodel.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInActivity : AppCompatActivity() {
    private var _binding: ActivityLogInBinding? = null
    private val binding: ActivityLogInBinding get() = requireNotNull(_binding)
    private lateinit var viewModel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLogInBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(LogInViewModel::class.java)
        setContentView(binding.root)

        initView()
        observeData()
    }

    private fun initView() {
        binding.logInBtn.setOnClickListener {
            val email = binding.userId.text.toString()
            val password = binding.userPw.text.toString()

            viewModel.login(email, password)
        }

        binding.signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeData() {
        with(viewModel) {
            errorMsg.observe(this@LogInActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    Toast.makeText(this@LogInActivity, it, Toast.LENGTH_SHORT).show()
                }
            }

            token.observe(this@LogInActivity) {
                SharedPref.saveToken(it)

                val intent = Intent(this@LogInActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
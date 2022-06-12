package com.team3.showbee.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.team3.showbee.databinding.ActivityUserAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserAccountActivity : AppCompatActivity() {
    private var _binding: ActivityUserAccountBinding? = null
    private val binding: ActivityUserAccountBinding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.changeUserinfoBtn.setOnClickListener {
            val intent = Intent(this, UpdateUserInfoActivity::class.java)
            startActivity(intent)
        }
    }
}
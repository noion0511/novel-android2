package com.team3.showbee.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.team3.showbee.R
import com.team3.showbee.databinding.ActivityBoardBinding
import com.team3.showbee.databinding.ActivityNovelListBinding
import com.team3.showbee.ui.adapter.FinancialContentAdapter
import com.team3.showbee.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardActivity : AppCompatActivity() {
    private var _binding: ActivityBoardBinding? = null
    private val binding: ActivityBoardBinding get() = requireNotNull(_binding)
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBoardBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(binding.root)

        initView()
        observeData()
    }

    private fun initView() {
        if (intent.hasExtra("pid")) {
            val pId = intent.getLongExtra("pid", 1)
            viewModel.setPost(pId)

            binding.btnDelete.setOnClickListener {
                viewModel.deletePost(pId)
            }

            binding.btnUpdate.setOnClickListener {
                val intent = Intent(this, AddPostActivity::class.java)
                    .putExtra("postId", pId)
                startActivity(intent)
            }
        }
    }

    private fun observeData() {
        with(viewModel) {
            post.observe(this@BoardActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    binding.title.text = it.title
                    binding.content.text = it.content
                }
            }
            bool.observe(this@BoardActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    Toast.makeText(this@BoardActivity, "성공했습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
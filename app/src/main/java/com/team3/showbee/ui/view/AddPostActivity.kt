package com.team3.showbee.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.team3.showbee.data.entity.Post
import com.team3.showbee.data.entity.PostRequestDto
import com.team3.showbee.databinding.ActivityAddFinancialBinding
import com.team3.showbee.databinding.ActivityAddPostBinding
import com.team3.showbee.ui.viewmodel.FinancialViewModel
import com.team3.showbee.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPostActivity : AppCompatActivity() {
    private var _binding: ActivityAddPostBinding? = null
    private val binding: ActivityAddPostBinding get() = requireNotNull(_binding)
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddPostBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(binding.root)

        initView()
        observeData()
    }

    private fun initView() {
        if (intent.hasExtra("id")) {
            val boardId = intent.getLongExtra("id", 1)

            binding.save.setOnClickListener {
                val post = PostRequestDto(title = binding.editTextTitle.text.toString(), content = binding.editTextContent.text.toString() ,boardId = boardId)
                viewModel.createPost(post)
            }
        }
        else if (intent.hasExtra("postId")) {
            val postId = intent.getLongExtra("postId", 1)

            viewModel.setPost(postId)

            binding.save.setOnClickListener {
                viewModel.updatePost(binding.editTextTitle.text.toString(), binding.editTextContent.text.toString(), postId)
            }
        }
    }

    private fun observeData() {
        with(viewModel) {
            msg.observe(this@AddPostActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    Toast.makeText(this@AddPostActivity, "성공했습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            post.observe(this@AddPostActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    binding.editTextTitle.setText(it.title)
                    binding.editTextContent.setText(it.content)
                }
            }
        }
    }
}
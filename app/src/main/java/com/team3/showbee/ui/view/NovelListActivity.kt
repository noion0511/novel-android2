package com.team3.showbee.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.team3.showbee.databinding.ActivityNovelListBinding
import com.team3.showbee.ui.adapter.FinancialContentAdapter
import com.team3.showbee.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NovelListActivity : AppCompatActivity() {
    private var _binding: ActivityNovelListBinding? = null
    private val binding: ActivityNovelListBinding get() = requireNotNull(_binding)
    private lateinit var viewModel: UserViewModel
    private lateinit var postAdapter: FinancialContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNovelListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(binding.root)

        initView()
        observeData()
    }

    private fun initView() {
        if (intent.hasExtra("id")) {
            val boardId = intent.getLongExtra("id", 1)
            viewModel.setPostList(boardId)
            viewModel.setBoard(boardId)

            val swipe = binding.layoutSwipe
            swipe.setOnRefreshListener {
                viewModel.setPostList(boardId)
                swipe.isRefreshing = false
            }

            binding.btnCreate.setOnClickListener {
                val intent = Intent(this, AddPostActivity::class.java)
                    .putExtra("id", boardId)
                startActivity(intent)
            }

            binding.btnDelete.setOnClickListener {
                viewModel.deleteBoard(boardId)
            }
        }
        postAdapter = FinancialContentAdapter()
        binding.recyclerview.adapter = postAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        postAdapter.setItemClickListener(object : FinancialContentAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int, pId: Long) {
                Log.d("board", "adapter: ${position}")
                val intent = Intent(this@NovelListActivity, BoardActivity::class.java)
                    .putExtra("pid", pId)
                startActivity(intent)
            }
        })
    }

    private fun observeData() {
        with(viewModel) {
            postList.observe(this@NovelListActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    postAdapter.setItems(it)
                    postAdapter.notifyDataSetChanged()
                }
            }
            board.observe(this@NovelListActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    binding.tvBoardTitle.text = it.title
                    binding.tvBoardWriter.text = it.writer
                    binding.tvCurrentHits.text = it.totalHits.toString()
                    binding.tvCreateDate.text = it.createdDate.split("T")[0]
                }
            }
            bool.observe(this@NovelListActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    Toast.makeText(this@NovelListActivity, "성공했습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
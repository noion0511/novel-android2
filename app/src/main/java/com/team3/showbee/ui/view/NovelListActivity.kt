package com.team3.showbee.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.team3.showbee.R
import com.team3.showbee.databinding.ActivityMainBinding
import com.team3.showbee.databinding.ActivityNovelListBinding
import com.team3.showbee.ui.adapter.BoardAdapter
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
        }
        postAdapter = FinancialContentAdapter()
        binding.recyclerview.adapter = postAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        postAdapter.setItemClickListener(object : FinancialContentAdapter.OnItemClickListener {
//            override fun onClick(v: View, position: Int, boardId: Long) {
//                Log.d("board", "adapter: ${position}")
//                val intent = Intent(this@NovelListActivity, NovelListActivity::class.java)
//                    .putExtra("id", boardId)
//                startActivity(intent)
//            }

            override fun onClick(v: View, position: Int) {
                Log.d("board", "adapter: ${position}")
            }
        })
    }

    private fun observeData() {
        with(viewModel) {
            post.observe(this@NovelListActivity) { event ->
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
        }
    }
}
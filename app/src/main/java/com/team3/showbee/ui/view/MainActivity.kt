package com.team3.showbee.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.team3.showbee.R
import com.team3.showbee.SharedPref
import com.team3.showbee.data.entity.Board
import com.team3.showbee.data.entity.Token
import com.team3.showbee.databinding.ActivityMainBinding
import com.team3.showbee.ui.adapter.BoardAdapter
import com.team3.showbee.ui.adapter.FinancialDayListAdapter
import com.team3.showbee.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = requireNotNull(_binding)
    private lateinit var viewModel: UserViewModel
    private lateinit var boardAdapter: BoardAdapter

    var triger = "financial"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel =ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(binding.root)

        initView()
        observeData()
    }

    private fun initView() {
        binding.mainNavigationView.setNavigationItemSelectedListener {
            Log.d("Info", "navigaion item click... ${it.title}")
            when(it.title) {
                "나의 계정" -> {
                    val intent = Intent(this, UserAccountActivity::class.java)
                    startActivity(intent)
                }
                "로그아웃" -> {
                    val dialog = LogOutDialog()
                    dialog.setButtonClickListener(object : LogOutDialog.OnButtonClickListener {
                        override fun onLogOutOkClicked() {
                            SharedPref.saveToken(Token(""))
                            val intent = Intent(this@MainActivity, LogInActivity::class.java)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    })
                    dialog.show(supportFragmentManager, "CustomDialog")
                }
                "회원탈퇴" -> {
                    val dialog = UserLeaveDialog()
                    dialog.setButtonClickListener(object : UserLeaveDialog.OnButtonClickListener {
                        override fun onLeaveOkClicked() {
                            viewModel.deleteUser()
                        }
                    })
                    dialog.show(supportFragmentManager, "CustomDialog")
                }
            }
            true
        }

        setSupportActionBar(binding.include.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.btnAddExpenseAndIncome.setOnClickListener {
            if (triger == "schedule") {
                val intent = Intent(this, AddIncomeExpenditureActivity::class.java)
                startActivity(intent)
            }
            else {
                val intent = Intent(this, AddFinancialActivity::class.java)
                startActivity(intent)
            }
        }

        boardAdapter = BoardAdapter()
        binding.recyclerview.adapter = boardAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        boardAdapter.setItemClickListener(object : BoardAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int, boardId: Long) {
                Log.d("board", "adapter: ${position}")
                val intent = Intent(this@MainActivity, NovelListActivity::class.java)
                    .putExtra("id", boardId)
                startActivity(intent)
            }
        })

        viewModel.setBoardList()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{ // 메뉴 버튼
                binding.mainDrawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeData() {
        with(viewModel) {
            msg.observe(this@MainActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                    if (it=="성공하였습니다.") {
                        val intent = Intent(this@MainActivity, LogInActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
            }

            token.observe(this@MainActivity) {
                SharedPref.saveToken(it)
            }

            boardList.observe(this@MainActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    boardAdapter.setItems(it)
                    boardAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}
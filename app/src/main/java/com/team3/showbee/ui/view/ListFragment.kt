package com.team3.showbee.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team3.showbee.R
import com.team3.showbee.data.entity.FinancialContentModel
import com.team3.showbee.data.entity.FinancialListModel
import com.team3.showbee.databinding.FragmentFinancialBinding
import com.team3.showbee.databinding.FragmentListBinding
import com.team3.showbee.ui.adapter.FinancialCalendarAdapter
import com.team3.showbee.ui.adapter.FinancialContentAdapter
import com.team3.showbee.ui.adapter.FinancialDayListAdapter
import com.team3.showbee.ui.viewmodel.BaseCalendar
import com.team3.showbee.ui.viewmodel.FinancialViewModel
import com.team3.showbee.ui.viewmodel.UserViewModel
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class ListFragment : Fragment(), FinancialCalendarAdapter.OnMonthChangeListener {
    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = requireNotNull(_binding)
    private val viewModel by activityViewModels<FinancialViewModel>()

    private lateinit var financialCalendarAdapter: FinancialCalendarAdapter
    private lateinit var financialDayListAdapter: FinancialDayListAdapter
    private val baseCalendar = BaseCalendar()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(layoutInflater)
        observeData()
        initView()
        return binding.root
    }

    fun initView() {
        financialCalendarAdapter = FinancialCalendarAdapter(onMonthChangeListener = this)
        financialDayListAdapter = FinancialDayListAdapter(requireContext())
        binding.listRv.layoutManager = LinearLayoutManager(context)
        binding.listRv.adapter = financialDayListAdapter

        baseCalendar.initBaseCalendar {
            onMonthChanged(it)
        }

        financialDayListAdapter.setItemClickListener(object : FinancialDayListAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                activity?.let {
                    Log.d("financial", "adapter: ${position}")
                }
            }
        })

        binding.fgCalPre.setOnClickListener {
            financialCalendarAdapter.changeToPrevMonth()
        }

        binding.fgCalNext.setOnClickListener {
            financialCalendarAdapter.changeToNextMonth()
        }
    }

    private fun observeData() {
        with(viewModel) {
            msg.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    if (it=="성공하였습니다.") {
                        val intent = Intent(context, LogInActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
            }
            list.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let {
                    Log.d("it------------------", "$it")
                    financialDayListAdapter.setItems(it)
                    financialDayListAdapter.notifyDataSetChanged()
                }
            }
            total.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let {
                    val dec = DecimalFormat("#,###원")
                    binding.incomeContent.text = dec.format(it[0])
                    binding.expenseContent.text = dec.format(it[1])
                }
            }
        }
    }

    override fun onMonthChanged(calendar: Calendar) {
        val sdf = SimpleDateFormat("yyyy년 MM월", Locale.KOREAN)
        val sdf2 = SimpleDateFormat("yyyy-MM", Locale.KOREAN)
        binding.fgCalMonth.text = sdf.format(calendar.time)
        viewModel.getMonthlyTotal(sdf2.format(calendar.time))
        Log.d("financial", "onMonthChanged")
        viewModel.getList(sdf2.format(calendar.time))
    }
}
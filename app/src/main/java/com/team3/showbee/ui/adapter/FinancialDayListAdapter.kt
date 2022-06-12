package com.team3.showbee.ui.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team3.showbee.data.entity.FinancialContentModel
import com.team3.showbee.data.entity.FinancialListModel
import com.team3.showbee.databinding.ListTitleItemBinding
import com.team3.showbee.ui.view.UpdateFinancialActivity
import com.team3.showbee.ui.viewmodel.BaseCalendar

class FinancialDayListAdapter(val context: Context): RecyclerView.Adapter<FinancialDayListAdapter.Holder>() {

    private var itemList: MutableMap<String, MutableList<FinancialContentModel>> = mutableMapOf()
    private lateinit var itemClickListener : FinancialDayListAdapter.OnItemClickListener

    private lateinit var financialContentAdapter: FinancialContentAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListTitleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(itemList, position)
//        holder.bind(itemList.list[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class Holder(var binding: ListTitleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MutableMap<String, MutableList<FinancialContentModel>>, position: Int) {
            Log.d("item", "$item")
            val list = item.keys.toList()
            val content = item[list[position]]

            binding.textView11.text = list[position]
//            financialContentAdapter = content?.let { FinancialContentAdapter(it) }!!
//            binding.recyclerview.adapter = financialContentAdapter
//            binding.recyclerview.layoutManager = LinearLayoutManager(context)
//
//            financialContentAdapter.setItemClickListener(object : FinancialContentAdapter.OnItemClickListener {
//                override fun onClick(v: View, position: Int) {
//                    Log.d("financial", "contentadapter: ${content[position].fid}")
//                    val intent = Intent(context, UpdateFinancialActivity::class.java)
//                        .putExtra("fid", content[position].fid)
//                    context.startActivity(intent)
//                }
//            })

            val pos = absoluteAdapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    itemClickListener.onClick(itemView,pos)
                }
            }
        }
    }

    fun setItems(item: MutableMap<String, MutableList<FinancialContentModel>>) {
        itemList.clear()
        itemList.putAll(item)
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: FinancialDayListAdapter.OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
}
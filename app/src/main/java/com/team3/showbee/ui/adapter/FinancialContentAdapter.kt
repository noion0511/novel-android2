package com.team3.showbee.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team3.showbee.data.entity.Board
import com.team3.showbee.data.entity.Financial
import com.team3.showbee.data.entity.FinancialContentModel
import com.team3.showbee.data.entity.Post
import com.team3.showbee.databinding.ListContentItemBinding
import java.text.DecimalFormat

class FinancialContentAdapter(): RecyclerView.Adapter<FinancialContentAdapter.Holder>() {

    private var itemList: MutableList<Post> = mutableListOf()
    private lateinit var itemClickListener : FinancialContentAdapter.OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListContentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class Holder(var binding: ListContentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            binding.textView13.text = item.pId.toString()
            binding.textView12.text = item.title
            binding.textView14.text = item.hits.toString()

            val pos = absoluteAdapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    itemClickListener.onClick(itemView, pos, item.pId)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int, pId: Long)
    }

    fun setItemClickListener(onItemClickListener: FinancialContentAdapter.OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    fun setItems(item: List<Post>) {
        itemList.clear()
        itemList.addAll(item)
    }
}

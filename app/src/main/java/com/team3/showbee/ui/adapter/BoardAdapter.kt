package com.team3.showbee.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team3.showbee.data.entity.Board
import com.team3.showbee.data.entity.FinancialContentModel
import com.team3.showbee.databinding.ItemBoardBinding

class BoardAdapter: RecyclerView.Adapter<BoardAdapter.Holder>() {

    private val itemList: MutableList<Board> = mutableListOf()
    private lateinit var itemClickListener : OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class Holder(var binding: ItemBoardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Board) {
            binding.tvBoardTitle.text = item.title
            binding.tvBoardWriter.text = item.writer
            binding.tvCreateDate.text = item.createdDate.split('T')[0]
            binding.tvCurrentHits.text = item.totalHits.toString()

            val pos = absoluteAdapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    itemClickListener.onClick(itemView,pos, item.id!!)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int, boardId: Long)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    fun setItems(item: List<Board>) {
        itemList.clear()
        itemList.addAll(item)
    }
}

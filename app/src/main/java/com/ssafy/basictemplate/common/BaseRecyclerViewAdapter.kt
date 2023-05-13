package com.ssafy.basictemplate.common

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<BIND : ViewDataBinding, DTO>(
    private var items: MutableList<DTO> = mutableListOf(),
) : RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseRecyclerViewHolder<BIND>>() {

    class BaseRecyclerViewHolder<BIND : ViewDataBinding>(var binding: BIND) :
        RecyclerView.ViewHolder(binding.root) {
    }

    abstract override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<BIND>

    override fun getItemCount(): Int = items.size

    fun clear() {
        items.clear()
        notifyItemRangeRemoved(0, itemCount)
    }

    fun add(data: DTO) {
        items += data
        notifyItemChanged(itemCount - 1)
    }

    fun addAll(datas: List<DTO>) {
        val prevSize = items.size
        items.addAll(datas)
        notifyItemRangeInserted(prevSize, datas.size)
    }
}
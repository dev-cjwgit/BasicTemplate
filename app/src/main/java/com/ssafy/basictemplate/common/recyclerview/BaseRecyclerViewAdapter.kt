package com.ssafy.basictemplate.common.recyclerview

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<BIND : ViewDataBinding, DTO>(
    protected var items: MutableList<DTO> = mutableListOf(),
) : RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseRecyclerViewHolder<BIND>>(),
    IRecyclerViewCD<DTO> {

    class BaseRecyclerViewHolder<BIND : ViewDataBinding>(var binding: BIND) :
        RecyclerView.ViewHolder(binding.root) {
    }

    abstract override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<BIND>

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    override fun initData(datas: MutableList<DTO>) {
        items = datas
        notifyDataSetChanged()
    }

    override fun clear() {
        items.clear()
        notifyItemRangeRemoved(0, itemCount)
    }

    override fun add(data: DTO) {
        items.add(data)
        notifyItemInserted(itemCount)
    }

    override fun remove(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun remove(data: DTO) {
        val position = items.indexOf(data)
        if (position != -1) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
package com.ssafy.basictemplate.common.recyclerview

interface IRecyclerViewCD<DTO> {
    // notifyDataSetChanged() 권장
    fun initData(datas: MutableList<DTO>)

    // notifyItemRangeRemoved(0, itemCount) 권장
    fun clear()

    // notifyItemChanged(itemCount - 1) 권장
    fun add(data: DTO)

    // notifyItemRemoved(position) 권장
    fun remove(position: Int)

    // notifyItemRemoved(position) 권장
    fun remove(data: DTO)
}
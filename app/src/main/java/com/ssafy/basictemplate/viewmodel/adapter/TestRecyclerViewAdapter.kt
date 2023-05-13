package com.ssafy.basictemplate.viewmodel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ssafy.basictemplate.R
import com.ssafy.basictemplate.common.recyclerview.BaseRecyclerViewAdapter
import com.ssafy.basictemplate.databinding.TemplateRecyclerviewTestBinding
import com.ssafy.basictemplate.model.domain.template.TestDTO

class TestRecyclerViewAdapter(
    private val onClickItem: (item: TestDTO) -> Unit,
    private val onClickDeleteButton: (pos: Int) -> Unit
) :
    BaseRecyclerViewAdapter<TemplateRecyclerviewTestBinding, TestDTO>(mutableListOf()) {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<TemplateRecyclerviewTestBinding> {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.template_recyclerview_test, viewGroup, false) //내가 각아이템에 사용하는 view
        val bind = TemplateRecyclerviewTestBinding.bind(view)
        val holder = BaseRecyclerViewHolder(bind)

        view.setOnClickListener {
            onClickItem.invoke(items[holder.adapterPosition]);
        }

        return holder
    }


    override fun onBindViewHolder(
        holder: BaseRecyclerViewHolder<TemplateRecyclerviewTestBinding>,
        position: Int
    ) {
        if (items.isNotEmpty()) {
            val listposition = items[position]
            // 데이터 주입
            holder.binding.item = listposition


            // 버튼 리스너 설정
            holder.binding.removeButton.setOnClickListener {
                if (holder.adapterPosition != -1) {
                    onClickDeleteButton.invoke(holder.adapterPosition)
                }
            }

        }
    }

}
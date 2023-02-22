package com.ssafy.basictemplate.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ssafy.basictemplate.R
import com.ssafy.basictemplate.databinding.FragmentMainBinding
import com.ssafy.basictemplate.databinding.FragmentSubBinding
import com.ssafy.basictemplate.viewmodel.activity.MainVM
import com.ssafy.basictemplate.viewmodel.fragment.MainFragmentVM
import com.ssafy.basictemplate.viewmodel.fragment.SubFragmentVM

class SubFragment : Fragment() {

    companion object {
        fun newInstance() = SubFragment()
    }

    private lateinit var viewModel: SubFragmentVM
    private lateinit var binding: FragmentSubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sub, container, false)
        activity?.let {
            viewModel = ViewModelProvider(it).get(SubFragmentVM::class.java)
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
        }
        return binding.root
    }
}
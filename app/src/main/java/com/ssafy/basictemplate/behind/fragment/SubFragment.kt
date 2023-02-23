package com.ssafy.basictemplate.behind.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.basictemplate.databinding.FragmentSubBinding
import com.ssafy.basictemplate.viewmodel.fragment.SubFragmentVM

class SubFragment : Fragment() {
    private lateinit var viewModel: SubFragmentVM
    private var _binding: FragmentSubBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubBinding.inflate(inflater, container, false)
        activity?.let {
            viewModel = ViewModelProvider(it).get(SubFragmentVM::class.java)
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
        }

        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
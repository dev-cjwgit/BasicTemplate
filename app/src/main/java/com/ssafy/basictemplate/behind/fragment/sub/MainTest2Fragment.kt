package com.ssafy.basictemplate.behind.fragment.sub

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ssafy.basictemplate.databinding.FragmentMainTest1Binding
import com.ssafy.basictemplate.databinding.FragmentMainTest2Binding
import com.ssafy.basictemplate.viewmodel.fragment.sub.MainTest1FragmentVM
import com.ssafy.basictemplate.viewmodel.fragment.sub.MainTest2FragmentVM

class MainTest2Fragment : Fragment() {
    private var _binding: FragmentMainTest2Binding? = null
    private lateinit var viewModel: MainTest2FragmentVM
    private lateinit var navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainTest2Binding.inflate(inflater, container, false)
        activity?.let {
            viewModel = ViewModelProvider(it)[MainTest2FragmentVM::class.java]
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
        }
        initObserve()
        setHasOptionsMenu(true)
        val root: View = binding.root


        return root
    }

    private fun initObserve() {
        viewModel.fragmentEvent.observe(requireActivity()) {
            it.getContentIfNotHandled()?.let {
                navController.navigate(it);
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireView())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Action Bar의 뒤로가기 버튼
        return when (item.itemId) {
            android.R.id.home -> {
                navController.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
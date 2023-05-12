package com.ssafy.basictemplate.behind.fragment

import android.content.Intent
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
import com.ssafy.basictemplate.R
import com.ssafy.basictemplate.databinding.FragmentMainBinding
import com.ssafy.basictemplate.viewmodel.fragment.MainFragmentVM

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private lateinit var viewModel: MainFragmentVM
    private lateinit var navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        activity?.let {
            viewModel = ViewModelProvider(it)[MainFragmentVM::class.java]
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
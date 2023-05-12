package com.ssafy.basictemplate.behind.fragment

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
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

        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
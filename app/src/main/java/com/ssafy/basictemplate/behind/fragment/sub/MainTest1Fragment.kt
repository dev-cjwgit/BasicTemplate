package com.ssafy.basictemplate.behind.fragment.sub

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ssafy.basictemplate.databinding.FragmentMainTest1Binding
import com.ssafy.basictemplate.viewmodel.fragment.sub.MainTest1FragmentVM

class MainTest1Fragment : Fragment() {
    private var _binding: FragmentMainTest1Binding? = null
    private lateinit var viewModel: MainTest1FragmentVM
    private lateinit var navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainTest1Binding.inflate(inflater, container, false)
        activity?.let {
            viewModel = ViewModelProvider(it)[MainTest1FragmentVM::class.java]
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
        }
        initObserve()
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                return super.onPrepareMenu(menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Action Bar의 뒤로가기 버튼
                return when (menuItem.itemId) {
                    android.R.id.home -> {
                        navController.popBackStack()
                        true
                    }
                    else -> false
                }
            }
        })
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.ssafy.basictemplate.behind.fragment.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.gson.JsonObject
import com.ssafy.basictemplate.behind.dialog.ConfirmDialog
import com.ssafy.basictemplate.behind.dialog.TestDetailDialog
import com.ssafy.basictemplate.common.interfaces.IBaseDialog
import com.ssafy.basictemplate.common.util.DialogType
import com.ssafy.basictemplate.databinding.FragmentMainTest1Binding
import com.ssafy.basictemplate.model.domain.template.TestDTO
import com.ssafy.basictemplate.viewmodel.fragment.main.MainTest1FragmentVM
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainTest1Fragment : Fragment() {
    companion object {
        val TAG: String? = this::class.qualifiedName
    }

    private var _binding: FragmentMainTest1Binding? = null
    private lateinit var viewModel: MainTest1FragmentVM
    private lateinit var navController: NavController

    private lateinit var fragmentActivity: FragmentActivity

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
        fragmentActivity = requireActivity()

        fragmentActivity.addMenuProvider(object : MenuProvider {
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

        return binding.root
    }

    private fun initObserve() {
        viewModel.fragmentEvent.observe(requireActivity()) {
            it.getContentIfNotHandled()?.let {
                navController.navigate(it);
            }
        }
        viewModel.dialogEvent.observe(requireActivity()) { it ->
            it.getContentIfNotHandled()?.let {
                when (it.getType()) {
                    DialogType.CONFIRM_DIALOG -> {
                        // TODO: requireContext(), requireActivity() 이면 main -> test-1 -> main -> test-1 -> test-2 로 갈 때 에러 발생함
                        //       java.lang.IllegalStateException: Fragment MainTest1Fragment{9dc6c48} (234ff9f9-f0c6-4f94-ae20-7924cfe490fc) not attached to a context.
                        val dialog = ConfirmDialog(fragmentActivity, object : IBaseDialog {
                            override fun confirm(jsonObject: JsonObject?) {
                                // 확인 버튼이 눌렸을 때 동작을 정의하는 람다식
                                // 전달된 JsonObject를 사용하여 원하는 작업을 수행합니다.
                                if (jsonObject?.get("result")?.asBoolean == true) {
                                    it.success()
                                }
                            }

                            override fun cancel() {
                                it.fail()
                            }
                        })
                        dialog.show()
                    }
                    else -> null
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireView())
        initObserve()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.ssafy.basictemplate.behind.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.ssafy.basictemplate.behind.dialog.ConfirmDialog
import com.ssafy.basictemplate.behind.dialog.TestDetailDialog
import com.ssafy.basictemplate.common.interfaces.IBaseDialog
import com.ssafy.basictemplate.common.util.DialogType
import com.ssafy.basictemplate.databinding.FragmentMainBinding
import com.ssafy.basictemplate.model.domain.template.TestDTO
import com.ssafy.basictemplate.viewmodel.fragment.MainFragmentVM

class MainFragment : Fragment() {
    companion object {
        val TAG: String? = this::class.qualifiedName
    }

    private var _binding: FragmentMainBinding? = null
    private lateinit var viewModel: MainFragmentVM
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
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        activity?.let {
            viewModel = ViewModelProvider(it)[MainFragmentVM::class.java]
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
            binding.adapter = viewModel.getAdapter()
        }

        fragmentActivity = requireActivity()

        val recyclerView = binding.recycler
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)


        return binding.root
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

    // region funcs
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

                    DialogType.TEST_DETAIL_DIALOG -> {
                        val dialog = TestDetailDialog(
                            it.getParams() as TestDTO,
                            fragmentActivity,
                            object : IBaseDialog {
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
                }
            }
        }
    }

    // endregion
}
package com.ssafy.basictemplate.behind.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.google.gson.JsonObject
import com.ssafy.basictemplate.common.interfaces.IBaseDialog
import com.ssafy.basictemplate.databinding.DialogTestDetailBinding
import com.ssafy.basictemplate.model.domain.template.TestDTO

class TestDetailDialog(
    data: TestDTO,
    context: Context,
    dialogInterface: IBaseDialog,
) :
    Dialog(context) {
    private var mBinding: DialogTestDetailBinding? = null
    private val binding get() = mBinding!!
    private var dialogInterface: IBaseDialog? = null
    private var data: TestDTO? = null;

    init {
        this.dialogInterface = dialogInterface
        this.data = data
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DialogTestDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.item = data

        binding.confirmButton.setOnClickListener {
            dismiss()
            val jsonObject = JsonObject()
            jsonObject.addProperty("result", true);
            dialogInterface?.confirm(jsonObject)

        }

        binding.cancelButton.setOnClickListener {
            dismiss()

            dialogInterface?.cancel()
        }
    }
}
package com.ssafy.basictemplate.behind.dialog

import android.content.Context
import android.os.Bundle
import com.google.gson.JsonObject
import com.ssafy.basictemplate.common.dialog.BaseDialog
import com.ssafy.basictemplate.common.dialog.IBaseDialog
import com.ssafy.basictemplate.databinding.DialogConfirmBinding

class ConfirmDialog(
    context: Context,
    dialogInterface: IBaseDialog,
) :
    BaseDialog(context, dialogInterface) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DialogConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
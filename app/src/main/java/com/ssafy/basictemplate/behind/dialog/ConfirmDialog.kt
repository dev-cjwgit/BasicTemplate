package com.ssafy.basictemplate.behind.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.google.gson.JsonObject
import com.ssafy.basictemplate.common.interfaces.IBaseDialog
import com.ssafy.basictemplate.databinding.DialogConfirmBinding

class ConfirmDialog(
    context: Context,
    dialogInterface: IBaseDialog,
) :
    Dialog(context) {
    private var mBinding: DialogConfirmBinding? = null
    private val binding get() = mBinding!!
    private var dialogInterface: IBaseDialog? = null

    init {
        this.dialogInterface = dialogInterface
    }

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
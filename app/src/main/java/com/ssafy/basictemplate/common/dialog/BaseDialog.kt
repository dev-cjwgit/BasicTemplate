package com.ssafy.basictemplate.common.dialog

import android.app.Dialog
import android.content.Context
import com.ssafy.basictemplate.databinding.DialogConfirmBinding

open class BaseDialog(
    context: Context,
    dialogInterface: IBaseDialog,
) : Dialog(context) {
    protected var mBinding: DialogConfirmBinding? = null
    protected val binding get() = mBinding!!

    protected var dialogInterface: IBaseDialog? = null

    init {
        this.dialogInterface = dialogInterface
    }
}
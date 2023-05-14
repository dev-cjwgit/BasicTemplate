package com.ssafy.basictemplate.common.interfaces

import com.ssafy.basictemplate.common.util.DialogType

interface IBaseConfirm<T> {
    fun success()

    fun fail()

    fun getType(): DialogType

    fun getParams(): T

}
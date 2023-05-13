package com.ssafy.basictemplate.common.util

interface IBaseConfirm<T> {
    fun success()

    fun fail()

    fun getType(): DialogType

    fun getParams(): T

}
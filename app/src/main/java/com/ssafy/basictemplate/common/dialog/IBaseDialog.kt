package com.ssafy.basictemplate.common.dialog

import com.google.gson.JsonObject

interface IBaseDialog {
    fun confirm(jsonObject: JsonObject?)

    fun cancel()
}
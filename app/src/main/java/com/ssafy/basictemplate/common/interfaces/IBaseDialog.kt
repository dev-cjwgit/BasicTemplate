package com.ssafy.basictemplate.common.interfaces

import com.google.gson.JsonObject

interface IBaseDialog {
    fun confirm(jsonObject: JsonObject?)

    fun cancel()
}
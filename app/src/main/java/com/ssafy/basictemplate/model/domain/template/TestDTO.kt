package com.ssafy.basictemplate.model.domain.template

import android.graphics.drawable.Drawable

data class TestDTO(
    var iconImage: Drawable?,

    var name: String,
    var level: Int,
    var address: String,
    var hobby: String,
    var extra: String,
)

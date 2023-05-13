package com.ssafy.basictemplate.model.service.refrofit

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @GET("/v1/images/search")
    fun getRandomUrlToCatPicture(): Call<JsonArray>
}
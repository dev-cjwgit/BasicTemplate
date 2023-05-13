package com.ssafy.basictemplate.model.service

import android.util.Log
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.ssafy.basictemplate.model.service.refrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestRetrofitService {
    companion object {
        val TAG: String? = this::class.qualifiedName

        const val baseUrl: String = "https://api.thecatapi.com"
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        var service = retrofit.create(RetrofitService::class.java);
    }

    fun getRandomUrlToCatPicture(onSuccess: (String) -> Unit, onFailure: (Throwable) -> Unit) {
        service.getRandomUrlToCatPicture().enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                if (response.isSuccessful) {
                    val jsonArray = response.body()
                    val jsonObject = jsonArray?.get(0)?.asJsonObject
                    val url = jsonObject?.get("url")?.asString
                    if (url != null) {
                        onSuccess(url)
                    } else {
                        onFailure(Throwable("Failed to parse URL"))
                    }
                } else {
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    onFailure(Throwable(response.toString()))
                }
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                onFailure(t)
            }
        })
    }

}
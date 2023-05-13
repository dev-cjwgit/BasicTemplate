package com.ssafy.basictemplate.common.util

import android.graphics.drawable.Drawable
import android.util.Log
import kotlinx.coroutines.*
import java.net.URL

object ImageLoader {

    @OptIn(DelicateCoroutinesApi::class)
    fun loadDrawableFromUrl(url: String, callback: (Drawable?) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                try {
                    val inputStream = URL(url).openStream()
                    Drawable.createFromStream(inputStream, null)
                } catch (e: Exception) {
                    null
                }
            }
            callback(result)
        }
    }
}
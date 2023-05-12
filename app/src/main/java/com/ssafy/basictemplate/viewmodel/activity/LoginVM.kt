package com.ssafy.basictemplate.viewmodel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.basictemplate.behind.activity.MainActivity

class LoginVM : ViewModel() {
    companion object {
        val TAG: String? = this::class.qualifiedName
    }

    // region Member Variable
    // REMOVE : 클래스 내부에서 필요한 멤버 변수를 정하는 곳
    private val _activityEvent = MutableLiveData<Class<*>>()
    val activityEvent: LiveData<Class<*>> get() = _activityEvent

    // endregion

    /**********************************************************************************************/

    // region VM Property
    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    // endregion

    /**********************************************************************************************/

    // region Button Handler
    fun loginButtonOnClick() {
        Log.d(TAG, "로그인")
        _activityEvent.value = MainActivity::class.java
    }
    // endregion

    /**********************************************************************************************/

    // region Design Property
    // REMOVE : 바인딩 할 디자인 멤버 변수를 정하는 곳
    // (ex : background, color 등)
    // endregion

    /**********************************************************************************************/

    // region Util
    // REMOVE : 기타 유틸 함수 등을 정하는 곳
    // endregion
}
package com.ssafy.basictemplate.viewmodel.fragment

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.basictemplate.R
import com.ssafy.basictemplate.util.Event
import com.ssafy.basictemplate.viewmodel.activity.LoginVM


class MainFragmentVM() : ViewModel() {
    companion object {
        val TAG: String? = this::class.qualifiedName
    }

    // region Member Variable
    // REMOVE : 클래스 내부에서 필요한 멤버 변수를 정하는 곳
    private val _fragmentEvent = MutableLiveData<Event<Int>>()
    val fragmentEvent: LiveData<Event<Int>> get() = _fragmentEvent

    // endregion

    /**********************************************************************************************/

    // region VM Property
    // REMOVE : 데이터 바인딩 할 멤버 변수를 정하는 곳
    // endregion

    /**********************************************************************************************/

    // region Button Handler
    // REMOVE : 버튼 이벤트 관련 정하는 곳

    fun nextButtonOnClick() {
        _fragmentEvent.postValue(Event(R.id.navigation_main_test_1))
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

    /**********************************************************************************************/
    // endregion
}
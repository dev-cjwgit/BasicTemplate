package com.ssafy.basictemplate.viewmodel.fragment.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.basictemplate.R
import com.ssafy.basictemplate.common.interfaces.IBaseConfirm
import com.ssafy.basictemplate.common.util.DialogType
import com.ssafy.basictemplate.common.util.Event
import com.ssafy.basictemplate.model.domain.Empty


class MainTest1FragmentVM() : ViewModel() {
    companion object {
        val TAG: String? = this::class.qualifiedName
    }

    // region Member Variable
    // REMOVE : 클래스 내부에서 필요한 멤버 변수를 정하는 곳
    private val _fragmentEvent = MutableLiveData<Event<Int>>()
    val fragmentEvent: LiveData<Event<Int>> get() = _fragmentEvent

    private val _dialogEvent = MutableLiveData<Event<IBaseConfirm<*>>>()
    val dialogEvent: LiveData<Event<IBaseConfirm<*>>> get() = _dialogEvent
    // endregion

    /**********************************************************************************************/

    // region VM Property
    // REMOVE : 데이터 바인딩 할 멤버 변수를 정하는 곳
    // endregion

    /**********************************************************************************************/

    // region Button Handler
    // REMOVE : 버튼 이벤트 관련 정하는 곳


    fun nextButtonOnClick() {
        _dialogEvent.postValue(
            Event(object : IBaseConfirm<Empty> {
                override fun success() {
                    _fragmentEvent.postValue(Event(R.id.navigation_main_test_2))
                }

                override fun fail() {

                }

                override fun getType(): DialogType {
                    return DialogType.CONFIRM_DIALOG
                }

                override fun getParams(): Empty {
                    throw NotImplementedError()
                }
            })
        )
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
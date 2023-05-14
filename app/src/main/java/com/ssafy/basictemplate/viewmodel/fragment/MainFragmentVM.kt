package com.ssafy.basictemplate.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.basictemplate.R
import com.ssafy.basictemplate.common.util.DialogType
import com.ssafy.basictemplate.common.util.Event
import com.ssafy.basictemplate.common.interfaces.IBaseConfirm
import com.ssafy.basictemplate.common.util.ImageLoader
import com.ssafy.basictemplate.model.domain.Empty
import com.ssafy.basictemplate.model.domain.template.TestDTO
import com.ssafy.basictemplate.model.service.TestRetrofitService
import com.ssafy.basictemplate.viewmodel.adapter.TestRecyclerViewAdapter


class MainFragmentVM() : ViewModel() {
    companion object {
        val TAG: String? = this::class.qualifiedName
    }

    // region Member Variable
    // REMOVE : 클래스 내부에서 필요한 멤버 변수를 정하는 곳

    private val _fragmentEvent = MutableLiveData<Event<Int>>()
    val fragmentEvent: LiveData<Event<Int>> get() = _fragmentEvent

    private val _dialogEvent = MutableLiveData<Event<IBaseConfirm<*>>>()
    val dialogEvent: LiveData<Event<IBaseConfirm<*>>> get() = _dialogEvent

    private val adapter: TestRecyclerViewAdapter
    // endregion

    init {
        adapter = TestRecyclerViewAdapter({
            _dialogEvent.postValue(
                Event(object : IBaseConfirm<TestDTO> {
                    override fun success() {
                    }

                    override fun fail() {

                    }

                    override fun getType(): DialogType {
                        return DialogType.TEST_DETAIL_DIALOG
                    }

                    override fun getParams(): TestDTO {
                        return it
                    }
                })
            )
        }, {
            removeToRecyclerView(it);
        })
    }
    /**********************************************************************************************/

    // region VM Property
    // REMOVE : 데이터 바인딩 할 멤버 변수를 정하는 곳
    // endregion

    /**********************************************************************************************/

    // region Button Handler
    // REMOVE : 버튼 이벤트 관련 정하는 곳
    private var index: Int = 0
    fun nextButtonOnClick() {
        _dialogEvent.postValue(
            Event(object : IBaseConfirm<Empty> {
                override fun success() {
                    _fragmentEvent.postValue(Event(R.id.navigation_main_test_1))
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

    fun addButtonOnClick() {
        TestRetrofitService().getRandomUrlToCatPicture(
            onSuccess = { url ->
                ImageLoader.loadDrawableFromUrl(url) { drawable ->
                    addToRecyclerView(
                        TestDTO(
                            drawable,
                            "제목 $index",
                            index,
                            "주소 $index",
                            "취미 $index",
                            "기타 $index"
                        )
                    )
                    index++
                }
            },
            onFailure = { err ->
                Log.e(TAG, err.message.toString())
            }
        )

    }

    fun exceptionButtonOnClick() {
        throw RuntimeException("임의 발생 테스트 예외예요~")
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
    fun getAdapter(): TestRecyclerViewAdapter {
        return adapter
    }

    /**********************************************************************************************/
    // endregion

    fun initDataToRecyclerView(datas: MutableList<TestDTO>) {
        adapter.initData(datas)
    }

    fun clearToRecyclerView() {
        adapter.clear()
    }

    fun removeToRecyclerView(position: Int) {
        adapter.remove(position)
    }

    fun removeToRecyclerView(data: TestDTO) {
        adapter.remove(data)
    }

    fun addToRecyclerView(data: TestDTO) {
        adapter.add(data)
    }
}
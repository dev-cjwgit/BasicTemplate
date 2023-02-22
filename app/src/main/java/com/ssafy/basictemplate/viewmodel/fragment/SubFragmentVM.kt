package com.ssafy.basictemplate.viewmodel.fragment

import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.basictemplate.util.ActivityCode
import com.ssafy.basictemplate.util.Event


class SubFragmentVM : ViewModel() {
    val message = MutableLiveData<String>("메인 프래그먼트입니다!")
}
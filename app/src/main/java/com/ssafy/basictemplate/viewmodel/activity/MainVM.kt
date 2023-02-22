package com.ssafy.basictemplate.viewmodel.activity

import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.basictemplate.util.ActivityCode
import com.ssafy.basictemplate.util.Event

class MainVM : ViewModel() {
    private val _currFragment: ObservableField<Fragment> = ObservableField<Fragment>()
    val currFragment: ObservableField<Fragment> get() = _currFragment
}
package com.ssafy.basictemplate.viewmodel.activity

import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel

class MainVM : ViewModel() {
    private val _currFragment: ObservableField<Fragment> = ObservableField<Fragment>()
    val currFragment: ObservableField<Fragment> get() = _currFragment
}
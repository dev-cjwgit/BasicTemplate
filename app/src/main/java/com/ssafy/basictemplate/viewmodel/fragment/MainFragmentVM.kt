package com.ssafy.basictemplate.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.basictemplate.viewmodel.activity.LoginVM
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.Web3ClientVersion
import org.web3j.protocol.http.HttpService


class MainFragmentVM : ViewModel() {
    val message = MutableLiveData<String>("메인 프래그먼트입니다!")

    fun test_onClick() {
        Log.d(
            LoginVM.TAG,
            "MainFragment - MainFragmentVM - 버튼 클릭"
        )

        val web3 =
            Web3j.build(HttpService("http://183.97.128.216:7545/")) // defaults to http://localhost:8545/

        val web3ClientVersion = web3.web3ClientVersion().sendAsync().get()
        Log.d(
            LoginVM.TAG,
            "데이터 " + web3ClientVersion.web3ClientVersion
        )
//        val web3 = Web3j.build(HttpService("http://183.97.128.216:7545/")) // defaults to http://localhost:8545/
//
//        web3.web3ClientVersion().flowable().subscribe { x: Web3ClientVersion ->
//            println(
//                x.web3ClientVersion
//            )
//        }

    }
}
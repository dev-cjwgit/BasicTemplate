package com.ssafy.basictemplate.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.basictemplate.blockchain.Contract_sol_Storage
import com.ssafy.basictemplate.viewmodel.activity.LoginVM
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import java.math.BigInteger
import kotlin.concurrent.thread


class MainFragmentVM : ViewModel() {
    val numberValue = MutableLiveData<String>("0")
    val statusText = MutableLiveData<String>("읽은 값")


    fun writeBtn_onClick() {
        Log.d(
            LoginVM.TAG,
            "MainFragment - MainFragmentVM - 버튼 클릭"
        )

//        val web3 =
//            Web3j.build(HttpService("http://183.97.128.216:7545/")) // defaults to http://localhost:8545/

        val web3 = Web3j.build(HttpService("https://mainnet.infura.io/v3/2849717dc6944af6a40ccf1540bdcb91"))
        val web3ClientVersion = web3.web3ClientVersion().sendAsync().get()
        Log.d(
            LoginVM.TAG,
            "데이터 " + web3ClientVersion.web3ClientVersion
        )
        // contract address
        val contractAddress = "0x8470FA00accd62c967b65C490913be1e7fbE421A"
        // gas limit
        val gasLimit: BigInteger = BigInteger.valueOf(300000)
        // gas price
        val gasPrice: BigInteger = BigInteger.valueOf(30)
        // create credentials w/ your private key
        val credentials =
            Credentials.create("cbbfa9f3fac6ffcbd8ab8431e645c737c3a796b1eebce200499c180615e39787")

        val contract =
            Contract_sol_Storage.load(contractAddress, web3, credentials, gasPrice, gasLimit)

        thread {
            // 값 읽기는 어떻게 읽는거냐-
//            val temp = contract.retrieve().sendAsync()
//            val value = temp.get()
//            Log.d(LoginVM.TAG, "recv result ${temp}")

            val data =
                contract.store(numberValue.value?.let { BigInteger.valueOf(it.toLong()) }).sendAsync()
//            Log.d(LoginVM.TAG, "send result ${data.get().blockNumber}, ${data.get().gasUsed}")
            numberValue.postValue("0")
        }
    }

    fun readBtn_onClick() {
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
        // contract address
        val contractAddress = "0xC89Ce4735882C9F0f0FE26686c53074E09B0D550"
        // gas limit
        val gasLimit: BigInteger = BigInteger.valueOf(3000000)
        // gas price
        val gasPrice: BigInteger = BigInteger.valueOf(3000)
        // create credentials w/ your private key
        val credentials =
            Credentials.create("4f3edf983ac636a65a842ce7c78d9aa706d3b113bce9c46f30d7d21715b23b1d")

        val contract =
            Contract_sol_Storage.load(contractAddress, web3, credentials, gasPrice, gasLimit)

        thread {
            // 값 읽기는 어떻게 읽는거냐-
            val temp = contract.retrieve().sendAsync()
            val value = temp.get()
            statusText.postValue(value.toString())
            Log.d(LoginVM.TAG, "recv result ${value}")
        }
    }
}
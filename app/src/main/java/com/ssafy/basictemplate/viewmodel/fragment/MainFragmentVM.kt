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
import java.util.concurrent.Future;
import kotlin.math.log


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
        // contract address
        val contractAddress = "0x5b1869D9A4C187F2EAa108f3062412ecf0526b24"
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
            // 값 읽기는 어떻게 읽는거냐
            val temp = contract.retrieve().sendAsync()
            val temp1 = temp.get()
            Log.d(LoginVM.TAG, "recv result ${temp}")

            // TODO: 값 쓰기는 되는데
            val data = contract.store(BigInteger.valueOf(50000)).sendAsync()
            Log.d(LoginVM.TAG, "send result ${data.get().blockNumber}, ${data.get().gasUsed}")
        }
//        Log.d(LoginVM.TAG, " ${contract.isValid}")
        println("")
        /*
        // read from contract
            val greeting: Future<String>? = greeter.greet().sendAsync()
            val convertToString: String? = greeting?.get()
            Log.d(TAG, "greeting value returned: $convertToString")
         */

        /*
            // write to contract
            val transactionReceipt: Future<TransactionReceipt>? = greeter.changeGreeting("Greeting changed from an Android App (ಠ_ಠ) ").sendAsync()
            val result = "Successful transaction. Gas used: ${transactionReceipt?.get()?.blockNumber}  ${transactionReceipt?.get()?.gasUsed}"
            Log.d(TAG, result)
         */


    }
}
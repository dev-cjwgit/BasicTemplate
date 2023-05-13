package com.ssafy.basictemplate.behind.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ssafy.basictemplate.R
import com.ssafy.basictemplate.databinding.ActivityLoginBinding
import com.ssafy.basictemplate.viewmodel.activity.LoginVM

class LoginActivity : AppCompatActivity() {
    companion object {
        val TAG: String? = this::class.qualifiedName
    }

    private val viewModel: LoginVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initObserve()
        setupGlobalExceptionHandling()
    }

    private fun initObserve() {
        viewModel.activityEvent.observe(this) {
            val intent = Intent(this, it);
            startActivity(intent)
        }
    }

    private fun setupGlobalExceptionHandling() {
        val defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            // 예외 처리 로직을 여기에 작성합니다.
            handleException(throwable)

            // TODO: 기본 예외 처리기 [정상 X]
            defaultExceptionHandler?.uncaughtException(thread, throwable)
        }
    }

    private fun handleException(throwable: Throwable) {
        // 예외 처리 로직을 구현합니다.
        // 예를 들어, 로그 기록, 사용자에게 오류 메시지 표시 등의 작업을 수행할 수 있습니다.
        try {
            Log.e(
                TAG, "\n----------------------------------------------------------\n" +
                        "${throwable.message.toString()}\n" +
                        "----------------------------------------------------------"
            )
        } catch (ex: java.lang.Exception) {
            Log.e(TAG, ex.message.toString())
        }
    }
}
package com.ssafy.basictemplate.behind.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ssafy.basictemplate.R
import com.ssafy.basictemplate.databinding.ActivityLoginBinding
import com.ssafy.basictemplate.util.ActivityCode
import com.ssafy.basictemplate.util.eventObserve
import com.ssafy.basictemplate.viewmodel.activity.LoginVM

class LoginActivity : AppCompatActivity() {
    companion object {
        const val TAG: String = "로그"
    }
    // Log.d(TAG, "LoginActivity - loginVM - pwEditText 라이브 데이터 값 변경 : $it")

    private val viewModel: LoginVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(LoginVM::class.java)
        val binding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

//        binding.apply {
//            lifecycleOwner = this@LoginActivity
//            viewModel = viewModel
//        }

        initObserve()

    }

    private fun initObserve() {
        viewModel.openEvent.eventObserve(this) { obj ->

            var intent: Intent? = null

            when (obj) {
                ActivityCode.MAIN_ACTIVITY -> intent = Intent(this, MainActivity::class.java)
                ActivityCode.LOGIN_ACTIVITY -> intent = Intent(this, LoginActivity::class.java)
            }

            startActivity(intent)
        }
    }
}
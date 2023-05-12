package com.ssafy.basictemplate.behind.activity

import android.content.Intent
import android.os.Bundle
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
    }

    private fun initObserve() {
        viewModel.activityEvent.observe(this) {
            val intent = Intent(this, it);
            startActivity(intent)
        }
    }
}
package com.ssafy.basictemplate.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.basictemplate.R
import com.ssafy.basictemplate.ui.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, MainFragment.newInstance())
                    .commitNow()
        }
    }

}
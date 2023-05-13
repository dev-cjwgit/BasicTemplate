package com.ssafy.basictemplate.behind.activity

import android.os.Bundle
import android.os.Process
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ssafy.basictemplate.R
import com.ssafy.basictemplate.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object {
        val TAG: String? = this::class.qualifiedName
    }

    private lateinit var navController: NavController
    private var toast: Toast? = null
    private var backKeyPressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val navView: BottomNavigationView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_main, R.id.navigation_sub
            )
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_main_test_1 -> {
                    binding.navView.menu.getItem(0).isChecked = true
                }

                R.id.navigation_main_test_2 -> {
                    binding.navView.menu.getItem(0).isChecked = true
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isAlwaysTopToCurrentFragment()) {
                    // 프래그먼트 스택이 없는 경우 handleOnBackPressed() 실행
                    if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
                        backKeyPressedTime = System.currentTimeMillis()
                        toast = Toast.makeText(
                            applicationContext,
                            "뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.",
                            Toast.LENGTH_LONG
                        )
                        toast?.show()
                        return
                    }
                    // 마지막으로 뒤로 가기 버튼을 눌렀던 시간에 2.5초를 더해 현재 시간과 비교 후
                    // 마지막으로 뒤로 가기 버튼을 눌렀던 시간이 2.5초가 지나지 않았으면 종료
                    if (System.currentTimeMillis() <= backKeyPressedTime + 2500) {
                        Process.killProcess(Process.myPid())
                        toast?.cancel()
                        toast =
                            Toast.makeText(applicationContext, "이용해 주셔서 감사합니다.", Toast.LENGTH_LONG)
                        toast?.show()
                    }
                } else {
                    // 프래그먼트 스택이 있는 경우 커스텀 동작 수행
                    // 여기서 프래그먼트에서의 메뉴 동작 처리를 추가로 구현
                    navController.popBackStack()
                }
            }
        })

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun isAlwaysTopToCurrentFragment(): Boolean {
        val currentDestination = navController.currentDestination
        // TODO: 최상위 프래그먼트가 늘어나면 수작업이 필요... @_@... 이게 맞나?
        return when (currentDestination?.id) {
            R.id.navigation_main, R.id.navigation_sub -> true
            else -> false
        }
    }
}
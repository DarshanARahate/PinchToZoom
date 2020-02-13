package com.app.pinchtozoom.ui.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Handler
import com.app.pinchtozoom.ui.mainscreen.MainActivity
import com.app.pinchtozoom.R


class SplashScreenActivity : AppCompatActivity() {

    private val TAG = "SplashScreenAct"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed(Runnable {
            // This method will be executed once the timer is over
            val i = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 5000)


    }


}
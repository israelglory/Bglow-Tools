package com.example.bglowcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler().postDelayed(Runnable { // your code to start second activity. Will wait for 3 seconds before calling this method
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
                                       }, 3000)
    }
}
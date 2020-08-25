package com.aboushanab.formulaone.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.aboushanab.formulaone.R

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    // This is the loading time of the splash screen
    private val splashtimeout:Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            startActivity(Intent(this, MainActivity::class.java))
            // close this activity
            finish()
        }, splashtimeout)
    }
}
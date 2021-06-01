package com.aemiralfath.decare.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.aemiralfath.decare.R
import com.aemiralfath.decare.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        const val TIME = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, TIME)

    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
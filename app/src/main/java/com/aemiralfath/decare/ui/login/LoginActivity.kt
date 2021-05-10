package com.aemiralfath.decare.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
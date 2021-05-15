package com.aemiralfath.decare.ui.earlydetection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aemiralfath.decare.databinding.ActivityEarlyDetectionBinding


class EarlyDetectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEarlyDetectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEarlyDetectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
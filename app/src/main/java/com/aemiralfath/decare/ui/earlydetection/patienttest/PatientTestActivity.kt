package com.aemiralfath.decare.ui.earlydetection.patienttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aemiralfath.decare.databinding.ActivityPatientTestBinding


class PatientTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPatientTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
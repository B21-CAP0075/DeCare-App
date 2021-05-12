package com.aemiralfath.decare.ui.earlydetection.introduction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.aemiralfath.decare.R

class PatientDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_data)

        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(InformationFragment::class.java.simpleName)

        if (fragment !is InformationFragment) {
            fragmentManager.commit {
                add(
                    R.id.fragment_container_patient,
                    InformationFragment(),
                    InformationFragment::class.java.simpleName
                )
            }
        }
    }
}
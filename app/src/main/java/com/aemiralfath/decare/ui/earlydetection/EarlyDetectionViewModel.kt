package com.aemiralfath.decare.ui.earlydetection

import android.util.Log
import androidx.lifecycle.ViewModel
import com.aemiralfath.decare.data.Patient

class EarlyDetectionViewModel : ViewModel() {
    private var dataPatient: Patient? = null

    fun addData(patient: Patient) {
        dataPatient = patient
        Log.d("ViewModelTest", dataPatient.toString())
    }
}
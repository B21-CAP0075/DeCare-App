package com.aemiralfath.decare.ui.earlydetection.introduction

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aemiralfath.decare.R
import com.aemiralfath.decare.data.Patient
import com.aemiralfath.decare.databinding.FragmentDataPatientBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.ui.earlydetection.patienttest.PatientTestActivity

class DataPatientFragment : Fragment() {

    companion object {
        private const val TAG = "DataPatientFragment"

        private val GENDER = arrayListOf("Laki-laki", "Perempuan")

        private val SES =
            arrayListOf("Kurang Mampu", "Pra Sejahtera", "Sederhana", "Kaya", "Konglemerat")

        private val EDUC =
            arrayListOf("SD", "SMP", "SMA", "D1", "D2", "D3", "S1", "S2", "S3", "Tidak ada")

        private val EDUC_MAP =
            mapOf(
                "SD" to 6,
                "SMP" to 9,
                "SMA" to 12,
                "D1" to 13,
                "D2" to 14,
                "D3" to 15,
                "S1" to 16,
                "S2" to 18,
                "S3" to 21,
                "Tidak ada" to 0
            )
    }

    private var _binding: FragmentDataPatientBinding? = null
    private val binding get() = _binding as FragmentDataPatientBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataPatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateForm()

        val viewModel = ViewModelProvider(
            viewModelStore,
            ViewModelProvider.NewInstanceFactory()
        ).get(EarlyDetectionViewModel::class.java)


        binding.btnStartExam.setOnClickListener {
            val patient = Patient(
                binding.edtPatientName.text.toString().trim(),
                binding.edtPatientAge.text.toString().trim().toInt(),
                checkGender(binding.spinnerGender.text.toString().trim()),
                checkEduc(binding.spinnerEduc.text.toString().trim()),
                checkSes(binding.spinnerSes.text.toString().trim())
            )

            Log.d(TAG, patient.toString())
            viewModel.addData(patient)
            startActivity(Intent(activity, PatientTestActivity::class.java))
        }

    }

    private fun checkSes(data: String): Int {
        return SES.indexOf(data)
    }

    private fun checkEduc(data: String): Int {
        return EDUC_MAP[data] ?: 0
    }

    private fun checkGender(data: String): Int {
        return if (data == GENDER[0]) {
            1
        } else {
            0
        }
    }

    private fun populateForm() {
        binding.spinnerGender.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                GENDER
            )
        )

        binding.spinnerEduc.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                EDUC
            )
        )

        binding.spinnerSes.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                SES
            )
        )

    }


}
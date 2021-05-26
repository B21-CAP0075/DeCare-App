package com.aemiralfath.decare.ui.earlydetection.introduction

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.data.model.Patient
import com.aemiralfath.decare.databinding.FragmentDataPatientBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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

    private val viewModel: EarlyDetectionViewModel by viewModel()

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

        binding.btnStartExam.setOnClickListener {

            val namePatient = binding.edtPatientName.text.toString().trim()
            val agePatient = binding.edtPatientAge.text.toString().trim()
            val genderPatient = binding.spinnerGender.text.toString().trim()
            val educPatient = binding.spinnerEduc.text.toString().trim()
            val sesPatient = binding.spinnerSes.text.toString().trim()

            when {
                namePatient.isEmpty() -> {
                    binding.edtPatientName.error = getString(R.string.empty)
                }
                agePatient.isEmpty() -> {
                    binding.edtPatientAge.error = getString(R.string.empty)
                }
                genderPatient.isEmpty() || !GENDER.contains(genderPatient) -> {
                    binding.spinnerGender.error = getString(R.string.empty)
                    binding.spinnerGender.setText("")
                }
                educPatient.isEmpty() || !EDUC.contains(educPatient) -> {
                    binding.spinnerEduc.error = getString(R.string.empty)
                    binding.spinnerEduc.setText("")
                }
                sesPatient.isEmpty() || !SES.contains(sesPatient) -> {
                    binding.spinnerSes.error = getString(R.string.empty)
                    binding.spinnerSes.setText("")
                }
                else -> {
                    val patient = Patient(
                        namePatient,
                        agePatient.toInt(),
                        checkGender(genderPatient),
                        checkEduc(educPatient),
                        checkSes(sesPatient)
                    )

                    Log.d(TAG, patient.toString())
                    viewModel.addData(patient)

                    findNavController().navigate(R.id.action_dataPatientFragment_to_questionOneFragment)
                }
            }

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
        binding.spinnerGender.inputType = InputType.TYPE_NULL

        binding.spinnerEduc.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                EDUC
            )
        )
        binding.spinnerEduc.inputType = InputType.TYPE_NULL


        binding.spinnerSes.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                SES
            )
        )
        binding.spinnerSes.inputType = InputType.TYPE_NULL

    }


}
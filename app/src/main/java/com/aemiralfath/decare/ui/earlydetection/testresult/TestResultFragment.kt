package com.aemiralfath.decare.ui.earlydetection.testresult

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aemiralfath.decare.databinding.FragmentTestResultBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestResultFragment : Fragment() {

    private var _binding: FragmentTestResultBinding? = null
    private val binding get() = _binding as FragmentTestResultBinding
    private val viewModel: EarlyDetectionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTestResultBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val patient = viewModel.getPatientData()
        binding.tvTestScoreTestResult.text = patient.mmse.toString()

        binding.btnPredictTestResult.setOnClickListener {
            viewModel.predict().observe(viewLifecycleOwner, {
                Log.d("ViewModelTest", "$it")
                val predict = "${it.prediction} (${it.confident}%)"
                binding.tvPredictResultTestResult.text = predict
            })
        }
    }
}
package com.aemiralfath.decare.ui.earlydetection.testresult

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentTestResultBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel

class TestResultFragment : Fragment() {

    private var _binding: FragmentTestResultBinding? = null
    private val binding get() = _binding as FragmentTestResultBinding

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

        val viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        ).get(EarlyDetectionViewModel::class.java)

        val patient = viewModel.getPatientData()
        patient?.let {
            binding.tvTestScoreTestResult.text = it.mmse.toString()
        }

        binding.btnPredictTestResult.setOnClickListener {
            viewModel.predict()
        }
    }
}
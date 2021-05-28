package com.aemiralfath.decare.ui.earlydetection.testresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.aemiralfath.decare.R
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
        binding.tvUserscoreTestResult.text = patient.mmse.toString()

        val testResultGreeting =
            String.format(resources.getString(R.string.test_result_greeting), patient.name)
        binding.tvCongratulationsTestResult.text = testResultGreeting

        binding.btnPredictTestResult.setOnClickListener {
            viewModel.predict().observe(viewLifecycleOwner, {

                binding.tvStatusTestResult.text = it.prediction

                if (it.prediction == "Normal") {
                    binding.imgStatusTestResult.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.root.context,
                            R.drawable.img_not_dementia
                        )
                    )
                } else {
                    binding.imgStatusTestResult.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.root.context,
                            R.drawable.img_dementia
                        )
                    )
                }
            })
        }

        viewModel.loadingStatePrediction.observe(viewLifecycleOwner, {
            showLoading(it)
        })

        binding.btnBackToHomeTestResult.setOnClickListener {
            activity?.finish()
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBarTestResult.visibility = View.VISIBLE
            binding.imgStatusTestResult.visibility = View.GONE
            binding.tvStatusTestResult.visibility = View.GONE
        } else {
            binding.progressBarTestResult.visibility = View.GONE
            binding.imgStatusTestResult.visibility = View.VISIBLE
            binding.tvStatusTestResult.visibility = View.VISIBLE
        }
    }
}
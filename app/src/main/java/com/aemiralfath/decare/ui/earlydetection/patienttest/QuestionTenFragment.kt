package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionTenBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.QuestionNumber
import org.koin.android.viewmodel.ext.android.viewModel

class QuestionTenFragment : Fragment() {

    private var _binding: FragmentQuestionTenBinding? = null
    private val binding get() = _binding as FragmentQuestionTenBinding
    private val viewModel: EarlyDetectionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionTenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionCount = String.format(resources.getString(R.string.question_count_placeholder), 10)
        binding.tvQuestionCountQuestionTen.text = questionCount

        binding.btnNextQuestionTen.setOnClickListener {
            viewModel.updatePatientAnswer(getAnswer(), QuestionNumber.TEN)
            findNavController().navigate(R.id.action_questionTenFragment_to_questionElevenFragment)
        }

    }

    private fun getAnswer() =
        binding.edtAnswerQuestionTen.editText?.text.toString()

}
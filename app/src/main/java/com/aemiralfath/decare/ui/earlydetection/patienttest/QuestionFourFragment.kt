package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionFourBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.QuestionNumber
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class QuestionFourFragment : Fragment() {

    private var _binding: FragmentQuestionFourBinding? = null
    private val binding get()= _binding as FragmentQuestionFourBinding
    private val viewModel by viewModel<EarlyDetectionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionFourBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionCount = String.format(resources.getString(R.string.question_count_placeholder), 4)
        binding.tvQuestionCountQuestionFour.text = questionCount

        binding.btnNextQuestionFour.setOnClickListener {
            viewModel.updatePatientAnswer(getAnswer(), QuestionNumber.FOUR)
            findNavController().navigate(R.id.action_questionFourFragment_to_questionFiveFragment)
        }
    }

    private fun getAnswer() : MutableList<Char> {
        val answer = binding.edtAnswerQuestionFour.editText?.text.toString()
        return answer.toMutableList()
    }

}
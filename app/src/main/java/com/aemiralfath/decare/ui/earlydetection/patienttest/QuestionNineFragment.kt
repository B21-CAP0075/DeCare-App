package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionNineBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.QuestionNumber

class QuestionNineFragment : Fragment() {

    private var _binding: FragmentQuestionNineBinding? = null
    private val binding get() = _binding as FragmentQuestionNineBinding

    private var isClicked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionNineBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionCount = String.format(resources.getString(R.string.question_count_placeholder), 9)
        binding.tvQuestionCountQuestionNine.text = questionCount

        val viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        ).get(EarlyDetectionViewModel::class.java)

        binding.btnQuestionNine.setOnClickListener {
            isClicked = true
            it.isEnabled = false
            binding.btnQuestionNine.text = resources.getString(R.string.clicked_button)
        }

        binding.btnNextQuestionNine.setOnClickListener {
            viewModel.updatePatientAnswer(isClicked, QuestionNumber.NINE)
            findNavController().navigate(R.id.action_questionNineFragment_to_questionTenFragment)
        }
    }
}
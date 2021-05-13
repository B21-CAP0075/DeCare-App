package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionElevenBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel

class QuestionElevenFragment : Fragment() {

    private var _binding: FragmentQuestionElevenBinding? = null
    private val binding get() = _binding as FragmentQuestionElevenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionElevenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionCount =
            String.format(resources.getString(R.string.question_count_placeholder), 11)
        binding.tvQuestionCountQuestionEleven.text = questionCount

        val viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        ).get(EarlyDetectionViewModel::class.java)

        binding.btnFinishQuestionEleven.setOnClickListener {
            viewModel.logAllAnswer()
            viewModel.logAllScore()
        }

    }


}
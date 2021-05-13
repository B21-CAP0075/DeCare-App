package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionSixBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.QuestionNumber

class QuestionSixFragment : Fragment() {

    private var _binding: FragmentQuestionSixBinding? = null
    private val binding get() = _binding as FragmentQuestionSixBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionSixBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionCount = String.format(resources.getString(R.string.question_count_placeholder), 6)
        binding.tvQuestionCountQuestionSix.text = questionCount

        val viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        ).get(EarlyDetectionViewModel::class.java)

        binding.btnNextQuestionSix.setOnClickListener {
            viewModel.updatePatientAnswer(getAnswer(), QuestionNumber.SIX)
            findNavController().navigate(R.id.action_questionSixFragment_to_questionSevenFragment)
        }

    }

    private fun getAnswer() : MutableList<String> {
        val listAnswer = mutableListOf<String>()

        listAnswer.clear() // menghapus list supaya tidak ada duplicate

        listAnswer.add(binding.edtFirstObjectQuestionSix.editText?.text.toString())
        listAnswer.add(binding.edtSecondObjectQuestionSix.editText?.text.toString())

        return listAnswer
    }


}
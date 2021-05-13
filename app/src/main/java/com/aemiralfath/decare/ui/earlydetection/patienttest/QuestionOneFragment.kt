package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionOneBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.QuestionNumber

class QuestionOneFragment : Fragment() {

    private var _binding: FragmentQuestionOneBinding? = null
    private val binding get()= _binding as FragmentQuestionOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionOneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionCount = String.format(resources.getString(R.string.question_count_placeholder), 1)
        binding.tvQuestionCountQuestionOne.text = questionCount

        val viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        ).get(EarlyDetectionViewModel::class.java)

        binding.btnNextQuestionOne.setOnClickListener {
//            if (isValidate()) {
//                findNavController().navigate(R.id.action_questionOneFragment_to_questionTwoFragment)
//            }
            viewModel.updatePatientAnswer(getAnswer(), QuestionNumber.ONE)
            findNavController().navigate(R.id.action_questionOneFragment_to_questionTwoFragment)
        }
    }

//    private fun isValidate(): Boolean {
//        if (TextUtils.isEmpty(binding.edtTimeQuestionOne.editText?.text)) {
//            binding.edtTimeQuestionOne.error = resources.getString(R.string.empty_error_field_message)
//            return false
//        }
//        if (TextUtils.isEmpty(binding.edtDaysQuestionOne.editText?.text)) {
//            binding.edtDaysQuestionOne.error = resources.getString(R.string.empty_error_field_message)
//            return false
//        }
//        if (TextUtils.isEmpty(binding.edtMonthQuestionOne.editText?.text)) {
//            binding.edtMonthQuestionOne.error = resources.getString(R.string.empty_error_field_message)
//            return false
//        }
//        if (TextUtils.isEmpty(binding.edtYearQuestionOne.editText?.text)) {
//            binding.edtYearQuestionOne.error = resources.getString(R.string.empty_error_field_message)
//            return false
//        }
//
//        return true
//    }

    private fun getAnswer() : MutableList<String> {
        val listAnswer = mutableListOf<String>()

        listAnswer.clear() // menghapus list supaya tidak ada duplicate

        listAnswer.add(binding.edtTimeQuestionOne.editText?.text.toString())
        listAnswer.add(binding.edtDaysQuestionOne.editText?.text.toString())
        listAnswer.add(binding.edtDateQuestionOne.editText?.text.toString())
        listAnswer.add(binding.edtMonthQuestionOne.editText?.text.toString())
        listAnswer.add(binding.edtYearQuestionOne.editText?.text.toString())

        return listAnswer
    }
}
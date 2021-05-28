package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionFiveBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.QuestionNumber
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionFiveFragment : Fragment() {

    private var _binding: FragmentQuestionFiveBinding? = null
    private val binding get() = _binding as FragmentQuestionFiveBinding
    private val viewModel by viewModel<EarlyDetectionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionFiveBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionCount =
            String.format(resources.getString(R.string.question_count_placeholder), 5)
        binding.tvQuestionCountQuestionFive.text = questionCount

        binding.btnNextQuestionFive.setOnClickListener {
            if (isValidate()) {
                viewModel.updatePatientAnswer(getAnswer(), QuestionNumber.FIVE)
                findNavController().navigate(R.id.action_questionFiveFragment_to_questionSixFragment)
            }
        }

    }

    private fun getAnswer(): MutableList<String> {
        val listAnswer = mutableListOf<String>()

        listAnswer.clear() // menghapus list supaya tidak ada duplicate

        listAnswer.add(binding.edtFirstObjectQuestionFive.editText?.text.toString())
        listAnswer.add(binding.edtSecondObjectQuestionFive.editText?.text.toString())
        listAnswer.add(binding.edtThirdObjectQuestionFive.editText?.text.toString())

        return listAnswer
    }

    private fun isValidate(): Boolean {
        if (TextUtils.isEmpty(binding.edtFirstObjectQuestionFive.editText?.text)) {
            binding.edtFirstObjectQuestionFive.error = resources.getString(R.string.empty)
            return false
        }
        if (TextUtils.isEmpty(binding.edtSecondObjectQuestionFive.editText?.text)) {
            binding.edtSecondObjectQuestionFive.error = resources.getString(R.string.empty)
            return false
        }
        if (TextUtils.isEmpty(binding.edtThirdObjectQuestionFive.editText?.text)) {
            binding.edtThirdObjectQuestionFive.error = resources.getString(R.string.empty)
            return false
        }

        return true
    }

}
package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionFourBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.AlertDialogHelper
import com.aemiralfath.decare.util.QuestionNumber
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionFourFragment : Fragment() {

    private var _binding: FragmentQuestionFourBinding? = null
    private val binding get() = _binding as FragmentQuestionFourBinding
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
        val questionCount =
            String.format(resources.getString(R.string.question_count_placeholder), 4)
        binding.tvQuestionCountQuestionFour.text = questionCount

        binding.btnNextQuestionFour.setOnClickListener {
            if (isValidate()) {
                viewModel.updatePatientAnswer(getAnswer(), QuestionNumber.FOUR)
                findNavController().navigate(R.id.action_questionFourFragment_to_questionFiveFragment)
            }
        }

        showAlertDialog()
    }

    private fun showAlertDialog() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                AlertDialogHelper.createAlertDialogHelper(
                    binding.root.context,
                    findNavController()
                ).apply { show() }
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun getAnswer(): MutableList<Char> {
        val answer = binding.edtAnswerQuestionFour.editText?.text.toString()
        return answer.toMutableList()
    }

    private fun isValidate(): Boolean {
        if (TextUtils.isEmpty(binding.edtAnswerQuestionFour.editText?.text)) {
            binding.edtAnswerQuestionFour.error = resources.getString(R.string.empty)
            return false
        }
        return true
    }

}
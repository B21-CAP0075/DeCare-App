package com.aemiralfath.decare.ui.earlydetection.validatortest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aemiralfath.decare.R
import com.aemiralfath.decare.data.model.PatientAnswer
import com.aemiralfath.decare.databinding.FragmentValidatorTestBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.AlertDialogHelper
import com.aemiralfath.decare.util.QuestionNumber
import org.koin.androidx.viewmodel.ext.android.viewModel

class ValidatorTestFragment : Fragment(), ValidatorTestAdapter.OnUpdateScoreClicked {

    private var _binding: FragmentValidatorTestBinding? = null
    private val binding get() = _binding as FragmentValidatorTestBinding

    private val viewModel: EarlyDetectionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentValidatorTestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showWarningDialog()

        setupRecyclerview(viewModel.getPatientAnswers())

        binding.btnCheckScoreValidator.setOnClickListener {
            viewModel.logAllScore()
            viewModel.updatePatientMMSE()
            findNavController().navigate(R.id.action_validatorTestFragment_to_testResultFragment)
        }

        showAlertDialog()

    }

    private fun showWarningDialog() {
        val alertDialogBuilder = AlertDialog.Builder(binding.root.context).apply {
            setTitle(context.getString(R.string.validator_message_title))
            setMessage(context.getString(R.string.validator_message))
            setCancelable(false)
            setPositiveButton(context.getString(R.string.yes)) { _, _ ->
            }
        }

        alertDialogBuilder.create().show()
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

    private fun setupRecyclerview(data: PatientAnswer) {
        val listAnswer = mutableListOf(
            data.firstAnswer.toString(),
            data.secondAnswer.toString(),
            data.sixthAnswer.toString(),
            data.tenthAnswer,
            ""
        )
        val answerBitmap = data.eleventhAnswer
        val context = binding.root.context

        val validatorTestAdapter = ValidatorTestAdapter()
        validatorTestAdapter.setData(listAnswer, answerBitmap, context, this)

        with(binding.rvAnswersValidator) {
            layoutManager = LinearLayoutManager(context)
            adapter = validatorTestAdapter
        }

    }

    override fun updateScore(position: Int, score: Int) {
        when (position) {
            0 -> { //jawaban no 1
                val number = QuestionNumber.ONE
                viewModel.updatePatientScore(score, number)
            }
            1 -> { //jawaban no 2
                val number = QuestionNumber.TWO
                viewModel.updatePatientScore(score, number)
            }
            2 -> { //jawaban no 6
                val number = QuestionNumber.SIX
                viewModel.updatePatientScore(score, number)
            }
            3 -> { //jawaban no 10
                val number = QuestionNumber.TEN
                viewModel.updatePatientScore(score, number)
            }
            4 -> { //jawaban no 11
                val number = QuestionNumber.ELEVEN
                viewModel.updatePatientScore(score, number)
            }
        }
    }


}
package com.aemiralfath.decare.ui.earlydetection.validatortest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aemiralfath.decare.R
import com.aemiralfath.decare.data.Patient
import com.aemiralfath.decare.data.PatientAnswer
import com.aemiralfath.decare.databinding.FragmentValidatorTestBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.QuestionNumber

class ValidatorTestFragment : Fragment(), ValidatorTestAdapter.OnUpdateScoreClicked {

    private var _binding: FragmentValidatorTestBinding? = null
    private val binding  get() = _binding as FragmentValidatorTestBinding

    private lateinit var viewModel: EarlyDetectionViewModel

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

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        ).get(EarlyDetectionViewModel::class.java)

        setupRecyclerview(viewModel.getPatientAnswers())

        binding.btnCheckScoreValidator.setOnClickListener {
            viewModel.logAllScore()
        }

        binding.btnSubmitScoreValidator.setOnClickListener {
            viewModel.updatePatientMMSE()

            findNavController().navigate(R.id.action_validatorTestFragment_to_testResultFragment)
        }
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
        when(position) {
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
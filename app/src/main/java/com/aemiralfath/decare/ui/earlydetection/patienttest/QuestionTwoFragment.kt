package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionTwoBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.QuestionNumber
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class QuestionTwoFragment : Fragment() {

    private var _binding: FragmentQuestionTwoBinding? = null
    private val binding get() = _binding as FragmentQuestionTwoBinding
    private val viewModel: EarlyDetectionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionTwoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionCount = String.format(resources.getString(R.string.question_count_placeholder), 2)
        binding.tvQuestionCountQuestionTwo.text = questionCount

        binding.btnNextQuestionTwo.setOnClickListener {
            viewModel.updatePatientAnswer(getAnswer(), QuestionNumber.TWO)
            findNavController().navigate(R.id.action_questionTwoFragment_to_questionThreeFragment)
        }

    }

    private fun getAnswer() : MutableList<String> {
        val listAnswer = mutableListOf<String>()

        listAnswer.clear() // menghapus list supaya tidak ada duplicate

        listAnswer.add(binding.edtCountryQuestionTwo.editText?.text.toString())
        listAnswer.add(binding.edtProvinceQuestionTwo.editText?.text.toString())
        listAnswer.add(binding.edtCityQuestionTwo.editText?.text.toString())
        listAnswer.add(binding.edtKecamatanQuestionTwo.editText?.text.toString())
        listAnswer.add(binding.edtKelurahanQuestionTwo.editText?.text.toString())

        return listAnswer
    }

}
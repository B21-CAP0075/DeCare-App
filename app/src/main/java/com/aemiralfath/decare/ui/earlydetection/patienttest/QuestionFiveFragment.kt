package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionFiveBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.QuestionNumber

class QuestionFiveFragment : Fragment() {

    private var _binding: FragmentQuestionFiveBinding? = null
    private val binding get() = _binding as FragmentQuestionFiveBinding

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

        val viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        ).get(EarlyDetectionViewModel::class.java)

        binding.btnNextQuestionFive.setOnClickListener {
            viewModel.updatePatientAnswer(getAnswer(), QuestionNumber.FIVE)
            findNavController().navigate(R.id.action_questionFiveFragment_to_questionSixFragment)
        }

    }

    private fun getAnswer() : MutableList<String> {
        val listAnswer = mutableListOf<String>()

        listAnswer.clear() // menghapus list supaya tidak ada duplicate

        listAnswer.add(binding.edtFirstObjectQuestionFive.editText?.text.toString())
        listAnswer.add(binding.edtSecondObjectQuestionFive.editText?.text.toString())
        listAnswer.add(binding.edtThirdObjectQuestionFive.editText?.text.toString())

        return listAnswer
    }

}
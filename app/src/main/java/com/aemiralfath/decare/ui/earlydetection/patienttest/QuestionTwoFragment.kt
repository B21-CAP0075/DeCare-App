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
import com.aemiralfath.decare.databinding.FragmentQuestionTwoBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.AlertDialogHelper
import com.aemiralfath.decare.util.QuestionNumber
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        val questionCount =
            String.format(resources.getString(R.string.question_count_placeholder), 2)
        binding.tvQuestionCountQuestionTwo.text = questionCount

        binding.btnNextQuestionTwo.setOnClickListener {
            if (isValidate()) {
                viewModel.updatePatientAnswer(getAnswer(), QuestionNumber.TWO)
                findNavController().navigate(R.id.action_questionTwoFragment_to_questionThreeFragment)
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

    private fun getAnswer(): MutableList<String> {
        val listAnswer = mutableListOf<String>()

        listAnswer.clear() // menghapus list supaya tidak ada duplicate

        listAnswer.add(binding.edtCountryQuestionTwo.editText?.text.toString())
        listAnswer.add(binding.edtProvinceQuestionTwo.editText?.text.toString())
        listAnswer.add(binding.edtCityQuestionTwo.editText?.text.toString())
        listAnswer.add(binding.edtKecamatanQuestionTwo.editText?.text.toString())
        listAnswer.add(binding.edtKelurahanQuestionTwo.editText?.text.toString())

        return listAnswer
    }

    private fun isValidate(): Boolean {
        if (TextUtils.isEmpty(binding.edtCountryQuestionTwo.editText?.text)) {
            binding.edtCountryQuestionTwo.error = resources.getString(R.string.empty)
            return false
        }
        if (TextUtils.isEmpty(binding.edtProvinceQuestionTwo.editText?.text)) {
            binding.edtProvinceQuestionTwo.error = resources.getString(R.string.empty)
            return false
        }
        if (TextUtils.isEmpty(binding.edtCityQuestionTwo.editText?.text)) {
            binding.edtCityQuestionTwo.error = resources.getString(R.string.empty)
            return false
        }
        if (TextUtils.isEmpty(binding.edtKecamatanQuestionTwo.editText?.text)) {
            binding.edtKecamatanQuestionTwo.error = resources.getString(R.string.empty)
            return false
        }
        if (TextUtils.isEmpty(binding.edtKelurahanQuestionTwo.editText?.text)) {
            binding.edtKelurahanQuestionTwo.error = resources.getString(R.string.empty)
            return false
        }

        return true
    }

}
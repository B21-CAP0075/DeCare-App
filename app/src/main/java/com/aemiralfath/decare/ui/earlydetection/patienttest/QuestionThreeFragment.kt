package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.media.SoundPool
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionThreeBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.QuestionNumber
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionThreeFragment : Fragment() {

    private lateinit var sp: SoundPool
    private var soundId: Int = 0
    private var spLoaded = false

    private var _binding: FragmentQuestionThreeBinding? = null
    private val binding get() = _binding as FragmentQuestionThreeBinding
    private val viewModel: EarlyDetectionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionThreeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionCount =
            String.format(resources.getString(R.string.question_count_placeholder), 3)
        binding.tvQuestionCountQuestionThree.text = questionCount

        setupSoundPool()

        binding.btnPlayAudioQuestionThree.setOnClickListener {
            if (spLoaded) {
                sp.play(soundId, 1f, 1f, 0, 0, 1f)
            }
        }

        binding.btnNextQuestionThree.setOnClickListener {
            if (isValidate()) {
                viewModel.updatePatientAnswer(getAnswer(), QuestionNumber.THREE)
                findNavController().navigate(R.id.action_questionThreeFragment_to_questionFourFragment)
            }
        }
    }

    private fun isValidate(): Boolean {
        if (TextUtils.isEmpty(binding.edtFirstObjectQuestionThree.editText?.text)) {
            binding.edtFirstObjectQuestionThree.error = resources.getString(R.string.empty)
            return false
        }
        if (TextUtils.isEmpty(binding.edtSecondObjectQuestionThree.editText?.text)) {
            binding.edtSecondObjectQuestionThree.error = resources.getString(R.string.empty)
            return false
        }
        if (TextUtils.isEmpty(binding.edtThirdObjectQuestionThree.editText?.text)) {
            binding.edtThirdObjectQuestionThree.error = resources.getString(R.string.empty)
            return false
        }

        return true
    }

    private fun setupSoundPool() {
        sp = SoundPool.Builder()
            .setMaxStreams(1)
            .build()

        sp.setOnLoadCompleteListener { _, _, status ->
            if (status == 0) {
                spLoaded = true
            } else {
                Log.d("QuestionThree", "Gagal Load")
            }
        }

        soundId = sp.load(binding.root.context, R.raw.apel_meja_koin, 1)
    }

    private fun getAnswer(): MutableList<String> {
        val listAnswer = mutableListOf<String>()

        listAnswer.clear() // menghapus list supaya tidak ada duplicate

        listAnswer.add(binding.edtFirstObjectQuestionThree.editText?.text.toString())
        listAnswer.add(binding.edtSecondObjectQuestionThree.editText?.text.toString())
        listAnswer.add(binding.edtThirdObjectQuestionThree.editText?.text.toString())

        return listAnswer
    }

}
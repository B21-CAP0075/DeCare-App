package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.media.SoundPool
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionSevenBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.AlertDialogHelper
import com.aemiralfath.decare.util.QuestionNumber
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionSevenFragment : Fragment() {

    private lateinit var sp: SoundPool
    private var soundId: Int = 0
    private var spLoaded = false

    private var _binding: FragmentQuestionSevenBinding? = null
    private val binding get() = _binding as FragmentQuestionSevenBinding
    private val viewModel: EarlyDetectionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionSevenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionCount =
            String.format(resources.getString(R.string.question_count_placeholder), 7)
        binding.tvQuestionCountQuestionSeven.text = questionCount

        setupSoundPool()

        binding.btnPlayAudioQuestionSeven.setOnClickListener {
            if (spLoaded) {
                sp.play(soundId, 1f, 1f, 0, 0, 1f)
            }
        }

        binding.btnNextQuestionSeven.setOnClickListener {
            if (isValidate()) {
                viewModel.updatePatientAnswer(getAnswer(), QuestionNumber.SEVEN)
                findNavController().navigate(R.id.action_questionSevenFragment_to_questionEightFragment)
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

    private fun setupSoundPool() {
        sp = SoundPool.Builder()
            .setMaxStreams(1)
            .build()
        sp.setOnLoadCompleteListener { _, _, status ->
            if (status == 0) {
                spLoaded = true
            } else {
                Log.d("QUESTION SEVEN", "Gagal Load")
            }
        }
        soundId = sp.load(binding.root.context, R.raw.jika_tidak_dan_atau_tapi, 1)
    }

    private fun getAnswer(): String {
        return binding.edtAnswerQuestionSeven.editText?.text.toString()
    }

    private fun isValidate(): Boolean {
        if (TextUtils.isEmpty(binding.edtAnswerQuestionSeven.editText?.text)) {
            binding.edtAnswerQuestionSeven.error = resources.getString(R.string.empty)
            return false
        }

        return true
    }

}
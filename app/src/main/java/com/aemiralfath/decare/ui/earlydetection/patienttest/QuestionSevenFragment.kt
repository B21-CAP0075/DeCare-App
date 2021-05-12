package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionSevenBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.QuestionNumber

class QuestionSevenFragment : Fragment() {

    private lateinit var sp: SoundPool
    private var soundId: Int = 0
    private var spLoaded = false

    private var _binding: FragmentQuestionSevenBinding? = null
    private val binding get() = _binding as FragmentQuestionSevenBinding

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

        val viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        ).get(EarlyDetectionViewModel::class.java)

        setupSoundPool()

        binding.btnPlayAudioQuestionSeven.setOnClickListener {
            if (spLoaded) {
                sp.play(soundId, 1f, 1f, 0, 0, 1f)
            }
        }

        binding.btnNextQuestionSeven.setOnClickListener {
            viewModel.updatePatientAnswer(getAnswer(), QuestionNumber.SEVEN)
            findNavController().navigate(R.id.action_questionSevenFragment_to_questionEightFragment)
        }

    }

    private fun setupSoundPool() {
        sp = SoundPool.Builder()
            .setMaxStreams(1)
            .build()
        sp.setOnLoadCompleteListener { soundPool, sampleId, status ->
            if (status == 0) {
                spLoaded = true
            } else {
                Log.d("QUESTION SEVEN", "Gagal Load")
            }
        }
        soundId = sp.load(binding.root.context, R.raw.jika_tidak_dan_atau_tapi, 1)
    }

    private fun getAnswer() : String {
        return binding.edtAnswerQuestionSeven.editText?.text.toString()
    }

}
package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionThreeBinding

class QuestionThreeFragment : Fragment() {

    private lateinit var sp: SoundPool
    private var soundId: Int = 0
    private var spLoaded = false

    private var _binding: FragmentQuestionThreeBinding? = null
    private val binding get() = _binding as FragmentQuestionThreeBinding

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

        setupSoundPool()

        binding.btnPlayAudioQuestionThree.setOnClickListener {
            if (spLoaded) {
                sp.play(soundId, 1f, 1f, 0, 0, 1f)
            }
        }

        binding.btnNextQuestionThree.setOnClickListener {
            findNavController().navigate(R.id.action_questionThreeFragment_to_questionFourFragment)
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
                Log.d("QUESTION THREE", "Gagal Load")
            }
        }

        soundId = sp.load(binding.root.context, R.raw.apel_meja_koin, 1)
    }

}
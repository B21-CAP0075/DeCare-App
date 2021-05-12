package com.aemiralfath.decare.ui.earlydetection.introduction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {

    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding as FragmentInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNextInstruction.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                replace(
                    R.id.fragment_container_patient,
                    InstructionFragment(),
                    InstructionFragment::class.java.simpleName
                )
            }
        }

    }
}
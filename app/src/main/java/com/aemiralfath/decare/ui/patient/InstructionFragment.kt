package com.aemiralfath.decare.ui.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {

    private var _binding: FragmentInstructionBinding? = null
    private val binding get() = _binding as FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnInputData.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                replace(
                    R.id.fragment_container_patient,
                    DataPatientFragment(),
                    DataPatientFragment::class.java.simpleName
                )
            }
        }

    }
}
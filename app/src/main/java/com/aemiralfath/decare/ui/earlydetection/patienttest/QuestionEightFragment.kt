package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionEightBinding
import com.google.android.material.card.MaterialCardView

class QuestionEightFragment : Fragment() {

    private var _binding: FragmentQuestionEightBinding? = null
    private val binding get() = _binding as FragmentQuestionEightBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionEightBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding.btnNextQuestionEight.setOnClickListener {
            findNavController().navigate(R.id.action_questionEightFragment_to_questionNineFragment)
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvQuestionEight) {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter = QuestionEightAdapter()
        }

        itemTouchHelper.attachToRecyclerView(binding.rvQuestionEight)

    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        }

    })


}
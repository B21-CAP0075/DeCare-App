package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentQuestionEightBinding
import com.aemiralfath.decare.ui.earlydetection.EarlyDetectionViewModel
import com.aemiralfath.decare.util.QuestionNumber

class QuestionEightFragment : Fragment(), QuestionEightAdapter.OnItemLongClickedListener {

    private val listCommand = mutableListOf("Tekan Tahan", "Geser Ke Kiri", "Geser Ke Kanan")
    private val listImgCommand =
        mutableListOf(R.drawable.ic_click, R.drawable.ic_swipe, R.drawable.ic_swipe)

    private var _binding: FragmentQuestionEightBinding? = null
    private val binding get() = _binding as FragmentQuestionEightBinding

    private lateinit var questionEightAdapter: QuestionEightAdapter
    var score = 0

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
        val questionCount = String.format(
            resources.getString(R.string.question_count_placeholder),
            8
        )
        binding.tvQuestionCountQuestionEight.text = questionCount

        val viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        ).get(EarlyDetectionViewModel::class.java)

        setupRecyclerView()

        binding.btnNextQuestionEight.setOnClickListener {
            viewModel.updatePatientAnswer(score, QuestionNumber.EIGHT)
            findNavController().navigate(R.id.action_questionEightFragment_to_questionNineFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        score = 0
    }

    private fun setupRecyclerView() {
        questionEightAdapter = QuestionEightAdapter()
        questionEightAdapter.setData(listCommand, listImgCommand)
        questionEightAdapter.setOnLongItemClickedListener(this)

        with(binding.rvQuestionEight) {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter = questionEightAdapter
        }

        itemTouchHelper.attachToRecyclerView(binding.rvQuestionEight)
    }

    private val itemTouchHelper = ItemTouchHelper(object :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (viewHolder is QuestionEightAdapter.ViewHolder) {
                val position = viewHolder.bindingAdapterPosition
                if (position == 1) {
                    if (direction == ItemTouchHelper.LEFT) {
                        score += 1
                    }
                }
                if (position == 2) {
                    if (direction == ItemTouchHelper.RIGHT) {
                        score += 1
                    }
                }
            }
        }
    })

    override fun updateScore(position: Int) {
        if (position == 0) {
            score += 1
        }
    }


}
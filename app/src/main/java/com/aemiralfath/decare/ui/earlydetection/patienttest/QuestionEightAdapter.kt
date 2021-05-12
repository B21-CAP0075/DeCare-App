package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.ItemQuestionEightBinding

class QuestionEightAdapter : RecyclerView.Adapter<QuestionEightAdapter.ViewHolder>() {

    private val listCommand = mutableListOf("Geser Ke Kiri", "Geser Ke Kanan", "Klik")
    private val listImgCommand =
        mutableListOf(R.drawable.ic_swipe, R.drawable.ic_swipe, R.drawable.ic_click)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuestionEightAdapter.ViewHolder {
        val view =
            ItemQuestionEightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionEightAdapter.ViewHolder, position: Int) {
        holder.bind(listCommand[position], listImgCommand[position])
    }

    override fun getItemCount() = 3

    inner class ViewHolder(private val binding: ItemQuestionEightBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(command: String, imgCommand: Int) {
            with(binding) {
                imgCommandItemQuestionEight.setImageResource(imgCommand)
                tvCommandItemQuestionEight.text = command
            }
        }
    }
}
package com.aemiralfath.decare.ui.earlydetection.patienttest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.decare.databinding.ItemQuestionEightBinding

class QuestionEightAdapter : RecyclerView.Adapter<QuestionEightAdapter.ViewHolder>() {
    private val listCommand = mutableListOf<String>()
    private val listImgCommand = mutableListOf<Int>()
    private lateinit var onItemLongClickedListener: OnItemLongClickedListener

    fun setData(commands: MutableList<String>, imgCommands: MutableList<Int>) {
        listCommand.clear()
        listImgCommand.clear()

        listCommand.addAll(commands)
        listImgCommand.addAll(imgCommands)
        notifyDataSetChanged()
    }

    fun setOnLongItemClickedListener(onItemLongClickedListener: OnItemLongClickedListener) {
        this.onItemLongClickedListener = onItemLongClickedListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuestionEightAdapter.ViewHolder {
        val view =
            ItemQuestionEightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view, onItemLongClickedListener)
    }

    override fun onBindViewHolder(holder: QuestionEightAdapter.ViewHolder, position: Int) {
        holder.bind(listCommand[position], listImgCommand[position])
    }

    override fun getItemCount() = 3

    inner class ViewHolder(
        private val binding: ItemQuestionEightBinding,
        private val longClickListener: OnItemLongClickedListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(command: String, imgCommand: Int) {
            with(binding) {
                imgCommandItemQuestionEight.setImageResource(imgCommand)
                tvCommandItemQuestionEight.text = command

                root.setOnLongClickListener {
                    val position = bindingAdapterPosition
                    longClickListener.updateScore(position)
                    root.visibility = View.GONE
                    true
                }
            }
        }
    }

    interface OnItemLongClickedListener {
        fun updateScore(position: Int)
    }
}
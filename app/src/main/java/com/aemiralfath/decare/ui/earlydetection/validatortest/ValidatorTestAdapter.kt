package com.aemiralfath.decare.ui.earlydetection.validatortest

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.ItemAnswerValidatorBinding

class ValidatorTestAdapter : RecyclerView.Adapter<ValidatorTestAdapter.ViewHolder>() {

    private val listCounter = mutableListOf(1, 2, 6, 10, 11)
    private val listQuestion = mutableListOf(
        "Jam, Hari, Tanggal, Bulan, Tahun",
        "Negara, Propinsi, Kota/Kabupaten, Kecamatan, Kelurahan",
        "Tunjukan gambar object pensil dan jam tangan",
        "Tulis sebuah kalimat lengkap yang memiliki subject dan predikat",
        "Gambarlah 2 segilima bersinggungan"
    )

    private val listAnswer = mutableListOf<String>()
    private lateinit var patientDrawable: Drawable
    private lateinit var context: Context
    private lateinit var onUpdateScoreClicked: OnUpdateScoreClicked

    fun setData(
        answers: MutableList<String>,
        answerDrawable: Drawable,
        context: Context,
        onUpdateScoreClicked: OnUpdateScoreClicked
    ) {
        listAnswer.clear()

        listAnswer.addAll(answers)
        patientDrawable = answerDrawable
        this.context = context
        this.onUpdateScoreClicked = onUpdateScoreClicked

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ValidatorTestAdapter.ViewHolder {
        val view =
            ItemAnswerValidatorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ValidatorTestAdapter.ViewHolder, position: Int) {
        holder.bind(listAnswer[position], listQuestion[position], listCounter[position], position)
    }

    override fun getItemCount() = listCounter.size

    inner class ViewHolder(private val binding: ItemAnswerValidatorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(answer: String, question: String, counter: Int, position: Int) {
            val questionCount = String.format(
                context.resources.getString(R.string.question_count_placeholder),
                counter
            )

            with(binding) {
                tvItemAnswerCount.text = questionCount
                tvQuestionItemValidator.text = question
                tvAnswerItemValidator.text = answer

                when (position) {
                    0 -> {
                        initSpinner(arrayListOf(0, 1, 2, 3, 4, 5), position)
                    }
                    1 -> {
                        initSpinner(arrayListOf(0, 1, 2, 3, 4, 5), position)
                    }
                    2 -> {
                        initSpinner(arrayListOf(0, 1, 2), position)
                    }
                    3 -> {
                        initSpinner(arrayListOf(0, 1), position)
                    }
                    4 -> {
                        initSpinner(arrayListOf(0, 1), position)
                        tvAnswerItemValidator.visibility = View.GONE
                        imgAnswerItemValidator.visibility = View.VISIBLE
                        imgAnswerItemValidator.setImageDrawable(patientDrawable)
                    }
                }

            }
        }

        private fun initSpinner(point: ArrayList<Int>, position: Int) {
            binding.spinnerPoint.setAdapter(
                ArrayAdapter(
                    context,
                    R.layout.support_simple_spinner_dropdown_item,
                    point
                )
            )
            binding.spinnerPoint.inputType = InputType.TYPE_NULL

            binding.spinnerPoint.setOnItemClickListener { _, _, _, _ ->
                val score =
                    binding.edtScoreItemValidator.editText?.text.toString().trim().toInt()
                Log.d("Spinner", "Point selected: $score | $position")
                onUpdateScoreClicked.updateScore(position, score)
            }

        }
    }


    interface OnUpdateScoreClicked {
        fun updateScore(position: Int, score: Int)
    }
}
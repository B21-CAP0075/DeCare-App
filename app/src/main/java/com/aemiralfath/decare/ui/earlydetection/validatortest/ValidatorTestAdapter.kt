package com.aemiralfath.decare.ui.earlydetection.validatortest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    fun setData(answers: MutableList<String>, answerDrawable: Drawable, context: Context, onUpdateScoreClicked: OnUpdateScoreClicked) {
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

                if (position == 4) {
                    imgAnswerItemValidator.visibility = View.VISIBLE
                    imgAnswerItemValidator.setImageDrawable(patientDrawable)
                }

                binding.btnUpdateScoreItemValidator.setOnClickListener {
                    val score = binding.edtScoreItemValidator.editText?.text.toString().trim().toInt()
                    onUpdateScoreClicked.updateScore(position, score)
                }

            }
        }
    }

    interface OnUpdateScoreClicked {
        fun updateScore(position: Int, score: Int)
    }
}
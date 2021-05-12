package com.aemiralfath.decare.ui.earlydetection

import android.util.Log
import androidx.lifecycle.ViewModel
import com.aemiralfath.decare.data.Patient
import com.aemiralfath.decare.data.PatientAnswer
import com.aemiralfath.decare.data.PatientTestScore
import com.aemiralfath.decare.util.AnyConverter
import com.aemiralfath.decare.util.QuestionNumber

class EarlyDetectionViewModel : ViewModel() {

    private var dataPatient: Patient? = null
    private val dataPatientTestScore = PatientTestScore()
    private val dataPatientAnswer = PatientAnswer()

    fun addData(patient: Patient) {
        dataPatient = patient
        Log.d("ViewModelTest", dataPatient.toString())
    }

    fun updatePatientAnswer(answer: Any, questionNumber: QuestionNumber) {
        when (questionNumber) {
            QuestionNumber.ONE -> {
                val filteredAnswer = AnyConverter.toListString(answer)
                dataPatientAnswer.firstAnswer = filteredAnswer
                Log.d("ViewModelTest", "patient first answer: ${dataPatientAnswer.firstAnswer}")
            }
            QuestionNumber.TWO -> {
                val filteredAnswer = AnyConverter.toListString(answer)
                dataPatientAnswer.secondAnswer = filteredAnswer
                Log.d("ViewModelTest", "patient second answer: ${dataPatientAnswer.secondAnswer}")
            }
            QuestionNumber.THREE -> {
                val filteredAnswer = AnyConverter.toListString(answer)
                var score = 0

                if (filteredAnswer[0].contains("apel", ignoreCase = true)) {
                    score += 1
                }
                if (filteredAnswer[1].contains("meja", ignoreCase = true)) {
                    score += 1
                }
                if (filteredAnswer[2].contains("koin", ignoreCase = true)) {
                    score += 1
                }

                dataPatientTestScore.thirdQuestionScore = score
                Log.d(
                    "ViewModelTest",
                    "patient third score: ${dataPatientTestScore.thirdQuestionScore}"
                )
            }
            QuestionNumber.FOUR -> {
                var score = 0
                val expectedAnswer = mutableListOf('U', 'Y', 'H', 'A', 'W')
                val filteredAnswer = AnyConverter.toListChar(answer)

                for (i in filteredAnswer.indices) {
                    if (filteredAnswer[i] == expectedAnswer[i]) {
                        score += 1
                    }
                }

                dataPatientTestScore.fourthQuestionScore = score
                Log.d(
                    "ViewModelTest",
                    "patient third score: ${dataPatientTestScore.fourthQuestionScore}"
                )
            }
            QuestionNumber.FIVE -> {
                val filteredAnswer = AnyConverter.toListString(answer)
                var score = 0

                if (filteredAnswer[0].contains("apel", ignoreCase = true)) {
                    score += 1
                }
                if (filteredAnswer[1].contains("meja", ignoreCase = true)) {
                    score += 1
                }
                if (filteredAnswer[2].contains("koin", ignoreCase = true)) {
                    score += 1
                }

                dataPatientTestScore.fifthQuestionScore = score
                Log.d(
                    "ViewModelTest",
                    "patient third score: ${dataPatientTestScore.fifthQuestionScore}"
                )
            }
            QuestionNumber.SIX -> {
                val filteredAnswer = AnyConverter.toListString(answer)
                dataPatientAnswer.sixthAnswer = filteredAnswer
                Log.d("ViewModelTest", "patient sixth answer: ${dataPatientAnswer.sixthAnswer}")
            }
            QuestionNumber.SEVEN -> {
                val patientAnswer = answer as String
                val splitAnswer = patientAnswer.split(" ")

                if (
                    splitAnswer[0].contains("jika", ignoreCase = true) &&
                    splitAnswer[1].contains("tidak", ignoreCase = true) &&
                    splitAnswer[2].contains("dan", ignoreCase = true) &&
                    splitAnswer[3].contains("atau", ignoreCase = true) &&
                    splitAnswer[4].contains("tapi", ignoreCase = true)
                ) {
                    dataPatientTestScore.seventhQuestionScore = 1
                    Log.d(
                        "ViewModelTest",
                        "patient seventh score: ${dataPatientTestScore.seventhQuestionScore}"
                    )
                }
            }
            QuestionNumber.EIGHT -> {

            }
            QuestionNumber.NINE -> {
                val isClicked = answer as Boolean

                if (isClicked) {
                    dataPatientTestScore.ninthQuestionScore = 1

                    Log.d(
                        "ViewModelTest",
                        "patient ninth score: ${dataPatientTestScore.ninthQuestionScore}"
                    )
                }
            }
            QuestionNumber.TEN -> {
                val patientAnswer = answer as String
                dataPatientAnswer.tenthAnswer = patientAnswer

                Log.d(
                    "ViewModelTest",
                    "patient tenth answer: ${dataPatientAnswer.tenthAnswer}"
                )
            }
            QuestionNumber.ELEVEN -> {
                //masih cari cara buat save gambar patient
            }
        }
    }
}
package com.aemiralfath.decare.ui.earlydetection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aemiralfath.decare.data.DecareRepository
import com.aemiralfath.decare.data.model.Patient
import com.aemiralfath.decare.util.QuestionNumber

class EarlyDetectionViewModel(private val decareRepository: DecareRepository) : ViewModel() {
    fun getPatientAnswers() = decareRepository.getPatientAnswers()
    fun getPatientData() = decareRepository.getPatientData()

    fun updatePatientMMSE() = decareRepository.updatePatientMMSE()
    fun logAllScore() = decareRepository.logAllScore()

    fun addData(patient: Patient) = decareRepository.addData(patient)

    fun updatePatientScore(score: Int, questionNumber: QuestionNumber) =
        decareRepository.updatePatientScore(score, questionNumber)

    fun updatePatientAnswer(answer: Any, questionNumber: QuestionNumber) =
        decareRepository.updatePatientAnswer(answer, questionNumber)

    fun predict() = decareRepository.predict()
}
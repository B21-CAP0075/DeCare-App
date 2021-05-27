package com.aemiralfath.decare.data

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.aemiralfath.decare.data.model.Patient
import com.aemiralfath.decare.data.model.PatientAnswer
import com.aemiralfath.decare.data.model.PatientTestScore
import com.aemiralfath.decare.data.source.local.LocalDataSource
import com.aemiralfath.decare.data.source.local.entity.ArticleEntity
import com.aemiralfath.decare.data.source.local.entity.ReminderEntity
import com.aemiralfath.decare.data.source.local.entity.YogaEntity
import com.aemiralfath.decare.data.source.remote.RemoteDataSource
import com.aemiralfath.decare.data.source.remote.network.ApiResponse
import com.aemiralfath.decare.data.source.remote.response.article.ArticleResponse
import com.aemiralfath.decare.data.source.remote.response.prediction.PredictionResponse
import com.aemiralfath.decare.data.source.remote.response.yoga.YogaResponse
import com.aemiralfath.decare.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DecareRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    private lateinit var dataPatient: Patient
    private val dataPatientTestScore = PatientTestScore()
    private val dataPatientAnswer = PatientAnswer()

    fun getPatientAnswers() = dataPatientAnswer
    fun getPatientData() = dataPatient

    fun updatePatientMMSE() {
        var totalScore = 0

        totalScore += dataPatientTestScore.firstQuestionScore
        totalScore += dataPatientTestScore.secondQuestionScore
        totalScore += dataPatientTestScore.thirdQuestionScore
        totalScore += dataPatientTestScore.fourthQuestionScore
        totalScore += dataPatientTestScore.fifthQuestionScore
        totalScore += dataPatientTestScore.sixthQuestionScore
        totalScore += dataPatientTestScore.seventhQuestionScore
        totalScore += dataPatientTestScore.eighthQuestionScore
        totalScore += dataPatientTestScore.ninthQuestionScore
        totalScore += dataPatientTestScore.tenthQuestionScore
        totalScore += dataPatientTestScore.eleventhQuestionScore

        dataPatient.mmse = totalScore
    }

    fun logAllScore() {
        Log.d("ViewModelAllScore", "first score: ${dataPatientTestScore.firstQuestionScore}")
        Log.d("ViewModelAllScore", "second score: ${dataPatientTestScore.secondQuestionScore}")
        Log.d("ViewModelAllScore", "third score: ${dataPatientTestScore.thirdQuestionScore}")
        Log.d("ViewModelAllScore", "fourth score: ${dataPatientTestScore.fourthQuestionScore}")
        Log.d("ViewModelAllScore", "fifth score: ${dataPatientTestScore.fifthQuestionScore}")
        Log.d("ViewModelAllScore", "sixth score: ${dataPatientTestScore.sixthQuestionScore}")
        Log.d("ViewModelAllScore", "seventh score: ${dataPatientTestScore.seventhQuestionScore}")
        Log.d("ViewModelAllScore", "eighth score: ${dataPatientTestScore.eighthQuestionScore}")
        Log.d("ViewModelAllScore", "ninth score: ${dataPatientTestScore.ninthQuestionScore}")
        Log.d("ViewModelAllScore", "tenth score: ${dataPatientTestScore.tenthQuestionScore}")
        Log.d("ViewModelAllScore", "eleventh score: ${dataPatientTestScore.eleventhQuestionScore}")
    }

    fun addData(patient: Patient) {
        dataPatient = patient
        Log.d("ViewModelTest", dataPatient.toString())
    }

    fun updatePatientScore(score: Int, questionNumber: QuestionNumber) {
        when (questionNumber) {
            QuestionNumber.ONE -> {
                dataPatientTestScore.firstQuestionScore = score
                Log.d(
                    "ViewModelTest",
                    "patient first score: ${dataPatientTestScore.firstQuestionScore}"
                )
            }
            QuestionNumber.TWO -> {
                dataPatientTestScore.secondQuestionScore = score
                Log.d(
                    "ViewModelTest",
                    "patient second score: ${dataPatientTestScore.secondQuestionScore}"
                )
            }
            QuestionNumber.SIX -> {
                dataPatientTestScore.sixthQuestionScore = score
                Log.d(
                    "ViewModelTest",
                    "patient sixth score: ${dataPatientTestScore.sixthQuestionScore}"
                )
            }
            QuestionNumber.TEN -> {
                dataPatientTestScore.tenthQuestionScore = score
                Log.d(
                    "ViewModelTest",
                    "patient tenth score: ${dataPatientTestScore.tenthQuestionScore}"
                )
            }
            QuestionNumber.ELEVEN -> {
                dataPatientTestScore.eleventhQuestionScore = score
                Log.d(
                    "ViewModelTest",
                    "patient eleventh score: ${dataPatientTestScore.eleventhQuestionScore}"
                )
            }
            else -> {

            }
        }
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
                    "patient fourth score: ${dataPatientTestScore.fourthQuestionScore}"
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
                    "patient fifth score: ${dataPatientTestScore.fifthQuestionScore}"
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
                val score = answer as Int

                dataPatientTestScore.eighthQuestionScore = score
                Log.d(
                    "ViewModelTest",
                    "patient eighth score: ${dataPatientTestScore.eighthQuestionScore}"
                )
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
                val drawable = answer as Drawable
                dataPatientAnswer.eleventhAnswer = drawable

                Log.d(
                    "ViewModelTest",
                    "patient eleventh answer: ${dataPatientAnswer.eleventhAnswer}"
                )
            }
        }
    }

    fun getYoga(): Flow<Resource<List<YogaEntity>>> =
        object : NetworkBoundResource<List<YogaEntity>, YogaResponse>() {
            override fun loadFromDB(): Flow<List<YogaEntity>> {
                return localDataSource.getYoga()
            }

            override fun shouldFetch(data: List<YogaEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<YogaResponse>> {
                return remoteDataSource.getYoga()
            }

            override suspend fun saveCallResult(data: YogaResponse) {
                val yogaEntities = Mapper.mapResponseToEntityYoga(data)
                localDataSource.insertYoga(yogaEntities)
            }

        }.asFlow()

    fun getArticle(): Flow<Resource<List<ArticleEntity>>> =
        object : NetworkBoundResource<List<ArticleEntity>, ArticleResponse>() {
            override fun loadFromDB(): Flow<List<ArticleEntity>> {
                return localDataSource.getArticle()
            }

            override fun shouldFetch(data: List<ArticleEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<ArticleResponse>> {
                return remoteDataSource.getArticle()
            }

            override suspend fun saveCallResult(data: ArticleResponse) {
                val articleEntities = Mapper.mapResponseToEntityArticle(data)
                localDataSource.insertArticle(articleEntities)
            }

        }.asFlow()

    fun getReminder(sort: String): LiveData<List<ReminderEntity>> {
        val query = SortUtils.getSortedQuery(sort)
        return localDataSource.getReminder(query).asLiveData()
    }

    fun insertReminder(reminder: ReminderEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.insertReminder(reminder)
        }
    }

    fun updateReminder(reminder: ReminderEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.updateReminder(reminder)
        }
    }

    fun deleteReminder(reminder: ReminderEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.deleteReminder(reminder)
        }
    }

    fun predict(): LiveData<PredictionResponse> {
        val jsonPatient = JsonObjectConverter.convertPatientToJson(dataPatient)
        return remoteDataSource.getPrediction(jsonPatient)
    }
}
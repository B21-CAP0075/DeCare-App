package com.aemiralfath.decare.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aemiralfath.decare.data.source.remote.network.ApiResponse
import com.aemiralfath.decare.data.source.remote.network.DecareApiService
import com.aemiralfath.decare.data.source.remote.response.article.ArticleResponse
import com.aemiralfath.decare.data.source.remote.response.prediction.PredictionResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val decareApiService: DecareApiService) {

    fun getArticle(): Flow<ApiResponse<ArticleResponse>> {
        return flow {
            try {
                val articles = decareApiService.getArticle()

                if (articles.isNotEmpty()) {
                    emit(ApiResponse.Success(articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getPrediction(json: JsonObject): LiveData<PredictionResponse> {
        val prediction = MutableLiveData<PredictionResponse>()

        val client = decareApiService.predict(json)
        client.enqueue(object : Callback<PredictionResponse> {
            override fun onResponse(
                call: Call<PredictionResponse>,
                response: Response<PredictionResponse>
            ) {
                val result = response.body()
                result?.let {
                    prediction.value = it
                    Log.d("ViewModelTest", "RemoteDataSource getPrediction: $it")
                }
            }

            override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                Log.d("ViewModelTest", "RemoteDataSource getPrediction: error with message ${t.message}")
            }

        })
        return prediction
    }

}
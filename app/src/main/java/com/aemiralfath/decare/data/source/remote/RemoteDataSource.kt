package com.aemiralfath.decare.data.source.remote

import com.aemiralfath.decare.data.source.remote.network.ApiResponse
import com.aemiralfath.decare.data.source.remote.network.DecareApiService
import com.aemiralfath.decare.data.source.remote.response.article.ArticleResponse
import com.aemiralfath.decare.data.source.remote.response.prediction.PredictionResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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

    fun getPrediction(json: JsonObject): Flow<ApiResponse<PredictionResponse>> {
        return flow {
            try {
                val prediction = decareApiService.predict(json)
                emit(ApiResponse.Success(prediction))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}
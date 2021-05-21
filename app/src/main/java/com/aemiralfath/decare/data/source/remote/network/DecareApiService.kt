package com.aemiralfath.decare.data.source.remote.network

import com.aemiralfath.decare.data.source.remote.response.article.ArticleResponse
import com.aemiralfath.decare.data.source.remote.response.prediction.PredictionResponse
import com.aemiralfath.decare.data.source.remote.response.yoga.YogaResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface DecareApiService {

    @Headers("Content-type: application/json")
    @POST("predict")
    fun predict(@Body body: JsonObject): PredictionResponse

    @GET("article")
    suspend fun getArticle() : ArticleResponse

    @GET("yoga")
    suspend fun getYoga() : YogaResponse
}
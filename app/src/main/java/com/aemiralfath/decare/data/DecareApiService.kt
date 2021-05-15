package com.aemiralfath.decare.data

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DecareApiService {

    @Headers("Content-type: application/json")
    @POST("predict")
    fun predict(@Body body: JsonObject): Call<PredictionResponse>

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://dementia-cares.uc.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        var service = retrofit.create(DecareApiService::class.java)
    }
}
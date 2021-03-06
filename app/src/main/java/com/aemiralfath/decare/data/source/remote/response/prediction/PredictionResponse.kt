package com.aemiralfath.decare.data.source.remote.response.prediction

import com.google.gson.annotations.SerializedName

data class PredictionResponse(
    @field:SerializedName("confident")
    val confident: Double,

    @field:SerializedName("prediction")
    val prediction: String
)
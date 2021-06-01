package com.aemiralfath.decare.data.model

data class Patient(
    val name: String? = "",
    val age: Int? = 0,
    val gender: Int? = 0,
    val educ: Int? = 0,
    val ses: Int? = 0,
    var mmse: Int? = 0
)
